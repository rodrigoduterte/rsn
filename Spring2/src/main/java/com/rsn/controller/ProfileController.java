package com.rsn.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.amazonaws.HttpMethod;
import com.rsn.entity.Profile;
import com.rsn.repository.*;
import com.rsn.service.AES;
import com.rsn.service.EmailContentMaker;
import com.rsn.service.S3Util;

import freemarker.template.TemplateException;

@RestController
@CrossOrigin
@SessionAttributes(value = "username")
public class ProfileController {

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private ActivatedProfileRepo activatedRepo;

	@Autowired
	private S3Util s3Util;

	@Autowired
	private JavaMailSender mailSenderObj;

	static EmailContentMaker emailContentMaker;

	@GetMapping(value = "/user/{username}")
	public Profile getUser(@PathVariable("username") String username) {
		String signedUrl = createSignedGetUrl(username);
		Profile profile = profileRepo.selectByUsername(username);
		profile.setPhoto(signedUrl);
		return profile;
	}

	@GetMapping(value = "/user/all")
	public List<Profile> getAllProfiles(@RequestParam("n") String name) {
		if (name == null || name.equals("")) {
			return profileRepo.selectAll();
		} else {
			return profileRepo.selectAllByName(name);
		}
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", // "application/json
			headers = "content-type=application/x-www-form-urlencoded")
	public boolean insert(@ModelAttribute Profile user) {
		user.setPassword(AES.encrypt(user.getPassword())); /// get unencrypted password and save as encrypted password
		if (profileRepo.insert(user) != null) {
			emailContentMaker = new EmailContentMaker(user.getEmail(), user.getUsername(), user.getFirstName(),
					user.getMiddleName(), user.getLastName(), activatedRepo.createNotExists(user, false));
			String emailSubject = "Successfully registered";

			mailSenderObj.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) {
					try {
						MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						mimeMsgHelperObj.setTo(user.getEmail());
						mimeMsgHelperObj.setFrom(System.getenv("em_username"));
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
		///
		String username = (String) session.getAttribute("username");
		return createSignedPutUrl(username);
	}

	@PutMapping(value = "/user/photo/{username}")
	public String createSignedPutUrl(@PathVariable String username) {
		String fileName = profileRepo.savePhoto(username);
		return s3Util.createSignedUrl(fileName, HttpMethod.PUT);
	}

	public String createSignedGetUrl(String username) {
		String fileName = profileRepo.getPhoto(username);
		if (fileName.equals("")) {
			return "";
		} else {
			return s3Util.createSignedUrl(fileName, HttpMethod.GET);
		}

	}

	@PostMapping(value = "/user/in")
	public @ResponseBody Profile login(Model model, HttpServletRequest request) {
		// public @ResponseBody Profile login(@ModelAttribute("user") Profile profile){

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			if (profileRepo.selectByUsername(username) == null) {
				return null;
			} else {
				Profile profile = profileRepo.selectByUsername(username);

				if (AES.decrypt(profile.getPassword()).equals(password)) {
					model.addAttribute("username", username);
					return profile;
				} else {
					return null;
				}
			}
		} catch (NoResultException e) {
			return null;
		}

	}

	@PostMapping(value = "/user/out")
	public void logout(Model model, @RequestBody Profile user) {
		//must delete session 
	}

	@GetMapping(value = "/user/activate")
	public void activate(@PathVariable("username") String username) {
		// activate account
		// redirect to login page
	}
}
