package com.rsn.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.apache.commons.lang3.RandomStringUtils;


import com.amazonaws.HttpMethod;
import com.rsn.entity.Profile;
import com.rsn.repository.ActivatedProfileRepo;
import com.rsn.repository.ProfileRepo;
import com.rsn.service.AES;
import com.rsn.service.EmailContentMaker;
import com.rsn.service.S3Util;
import com.rsn.service.Validator;

import freemarker.template.TemplateException;

@RestController
@CrossOrigin
@SessionAttributes(value = "username")
@PropertySource("classpath:app.properties")
public class ProfileController {

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private ActivatedProfileRepo activatedRepo;

	@Autowired
	private S3Util s3Util;

	@Autowired
	private JavaMailSender mailSenderObj;

	@Autowired
	private Validator validator;
	
	static EmailContentMaker emailContentMaker;

	
	static String recipient;
	
	@Autowired
	private AES aes;
	
	@Value("${em_username}")
	public void setRecipient(String recip) {
		recipient = recip;
	}
	
	
	@GetMapping(value = "/user/{username}")
	public Profile getUser(@PathVariable("username") String username) {
		System.out.println();
		String signedUrl = s3Util.createSignedGetUrl(profileRepo.getPhoto(username)); 
		Profile profile = profileRepo.selectByUsername(username);
		profile.setPhoto(signedUrl);
		return profile;
	}

	@CrossOrigin
	@GetMapping(value = "/user/all")
	public List<Profile> getAllProfiles(@RequestParam(name = "n", defaultValue = "") String name) {
		List<Profile> profiles = null;
		if (name.equals("")) {
			profiles = profileRepo.selectAll();
			for (Profile profile : profiles) {
				String username = profile.getUsername();
				String signedUrl = s3Util.createSignedGetUrl(profileRepo.getPhoto(username));
				profile.setPhoto( signedUrl );
			}
		} else {
			profiles = profileRepo.selectAllByName( name );
			for (Profile profile : profiles) {
				String username = profile.getUsername();
				String signedUrl = s3Util.createSignedGetUrl(profileRepo.getPhoto(username));
				profile.setPhoto( signedUrl );
			}
		}
		return profiles;
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", // "application/json
			headers = "content-type=application/x-www-form-urlencoded")
	public boolean insert(@ModelAttribute Profile user) {
		user.setPassword(aes.encrypt(user.getPassword())); /// get unencrypted password and save as encrypted password
		if (profileRepo.insert(user) != null) {
			emailContentMaker = new EmailContentMaker(user.getEmail(), user.getUsername(), user.getFirstName(),
					user.getMiddleName(), user.getLastName(), activatedRepo.createNotExists(user, false));
			String emailSubject = "Successfully registered";

			mailSenderObj.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) {
					try {
						MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						mimeMsgHelperObj.setTo(user.getEmail());
						mimeMsgHelperObj.setFrom(recipient); //System.getenv("em_username")
						mimeMsgHelperObj.setText(emailContentMaker.build(), true);
						mimeMsgHelperObj.setSubject(emailSubject);
					} catch (MessagingException | TemplateException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			return true;
		} else {
			return false;
		}
	}

	@PostMapping(value = "/user/edit")
	public String update(@RequestBody Profile user, HttpSession session) {
		/// String username = user.getUsername();
		String username = (String) session.getAttribute("username");
		
		String fileName = profileRepo.savePhoto(username);
		profileRepo.update(user);
		return s3Util.createSignedPutUrl(fileName);
	}

	@PutMapping(value = "/user/photo/{username}")
	public String createPhoto(@PathVariable String username) {
		String fileName = profileRepo.savePhoto(username);
		return s3Util.createSignedPutUrl(fileName);
		//return s3Util.s3Client.getRegion().toString();
	}

	@PostMapping(value = "/user/in")
	public String login(Model model, HttpServletRequest request) {
		// public @ResponseBody Profile login(@ModelAttribute("user") Profile profile){

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			if(activatedRepo.isActivated(username)) {
				if (profileRepo.selectByUsername(username) == null) {
					return "User not found";
				} else {
					Profile profile = profileRepo.selectByUsername(username);

					if (aes.decrypt(profile.getPassword()).equals(password)) {
						model.addAttribute("username", username);
						return "User found";
					} else {
						return "Password Invalid";
					}
				}
			} else {
				return "User not activated";
			}
			
			
		} catch (NoResultException e) {
			return "User not found";
		}

	}

	@GetMapping(value = "/user/out/{username}")
	public boolean logout(@PathVariable String username) {
		//must delete session
		return true;
	}

	@GetMapping(value = "/user/activate/{activeId}")
	public void activate(@PathVariable("activeId") String activeId) {
		// activate account
		// redirect to login page
		activatedRepo.activate(activeId);
	}
	
	///added after deploy to ec2
	@GetMapping(value = "/user/forgot/{emailOrUsername}")
	public void changePassword(@PathVariable("emailOrUsername") String emailOrUsername) {
		if (validator.isEmail(emailOrUsername)) {
			String newPassword = RandomStringUtils.random(5, true, true);
			Profile user = profileRepo.selectByEmail(emailOrUsername);
			user.setPassword(newPassword);
			profileRepo.update(user);
			
			String emailSubject = "New Password";
			
			emailContentMaker = new EmailContentMaker(user.getEmail(), user.getUsername(), user.getFirstName(),
					user.getMiddleName(), user.getLastName(), activatedRepo.createNotExists(user, false));
			
			
			mailSenderObj.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) {
					try {
						MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						mimeMsgHelperObj.setTo(user.getEmail());
						mimeMsgHelperObj.setFrom(recipient); //System.getenv("em_username")
						mimeMsgHelperObj.setText(emailContentMaker.build(), true);
						mimeMsgHelperObj.setSubject(emailSubject);
					} catch (MessagingException | TemplateException | IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
