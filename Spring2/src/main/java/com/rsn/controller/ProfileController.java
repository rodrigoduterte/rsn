package com.rsn.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rsn.dto.Login;
import com.rsn.entity.Profile;
import com.rsn.repository.ActivatedProfileRepo;
import com.rsn.repository.ProfileRepo;
import com.rsn.service.AES;
import com.rsn.service.EmailContentMaker;
import com.rsn.service.S3Util;
import com.rsn.service.Validator;
import com.rsn.dto.EditProfile;

import freemarker.template.TemplateException;

/**
 * @author Gabriel Ferrer Is a Controller that has REST Endpoints for adding,
 *         editing and viewing profiles. REST Endpoints GET
 *         /user/info/{username} : gets the info of a specific profile(user) GET
 *         /user/all : gets all the info of all profiles(users) GET
 *         /user/all?name=searchKey : gets all the info of matching searchKey
 *         POST /user/new : creates a new profile(user) and sends email to user
 *         email POST /user/edit/{username} : edits the profile of a user POST
 *         /user/in : checks whether the supplied user credentials exist in the
 *         database GET /user/out/{username} : removes the session from the
 *         logged in user GET /user/forgot/{emailOrUsername} : sends user an
 *         email of the new password
 * 
 * 
 */
@RestController
@CrossOrigin
@SessionAttributes(value = "username")
@PropertySource("classpath:app.properties")
public class ProfileController {

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private ActivatedProfileRepo activatedProfileRepo;

	@Autowired
	private S3Util s3Util;

	@Autowired
	private JavaMailSender mailSenderObj;

	@Autowired
	private Validator validator;

	static EmailContentMaker emailContentMaker;

	static String sender;

	@Autowired
	private AES aes;

	@Value("${em_username}")
	public void setSender(String sent) {
		sender = sent;
	}

	@GetMapping(value = "/user/info/{username}")
	public Profile getUser(@PathVariable("username") String username) {
		String signedUrl = s3Util.createSignedGetUrl(profileRepo.getPhoto(username));
		Profile profile = profileRepo.selectByUsername(username);
		profile.setPhoto(signedUrl);
		return profile;
	}

	@CrossOrigin
	@GetMapping(value = "/user/all")
	public List<Profile> getAllProfiles(@RequestParam(name = "n", defaultValue = "") String name,
			@RequestParam(name = "part", defaultValue = "") String part) {
		List<Profile> profiles = null;

		if (name.equals("")) {
			profiles = profileRepo.selectAll();
			for (Profile profile : profiles) {
				String username = profile.getUsername();
				String signedUrl = s3Util.createSignedGetUrl(profileRepo.getPhoto(username));
				profile.setPhoto(signedUrl);
			}
		} else {
			profiles = profileRepo.selectAllByName(name);
			for (Profile profile : profiles) {
				String username = profile.getUsername();
				String signedUrl = s3Util.createSignedGetUrl(profileRepo.getPhoto(username));
				profile.setPhoto(signedUrl);
			}
		}
		return profiles;
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String insert(@RequestBody Profile user) {
		if (validator.isValidPassword(user.getPassword()) && validator.isEmail(user.getEmail())
				&& !profileRepo.exists(user.getEmail()) && !profileRepo.exists(user.getUsername())) {
			user.setPassword(aes.encrypt(user.getPassword())); /// get unencrypted password and save as encrypted
																/// password
		} else {
			return "Invalid registration";
		}

		if (profileRepo.insert(user) != null) {

			emailContent("activate", user, "", "Successfully registered",
					activatedProfileRepo.createNotExists(user, false));

			return "Registration successful";
		} else {
			return "Invalid registration";
		}
	}

	@RequestMapping(value = "/user/edit/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody EditProfile userJSON, @PathVariable String username) {
		Profile userDB = profileRepo.selectByUsername(username);

		if (validator.isEmail(userJSON.getEmail())) {
			userDB.setProfile(userJSON);
		}

		profileRepo.update(userDB);
		return "Edit user success";
	}

	@PutMapping(value = "/user/photo/{username}")
	public String createPhoto(@PathVariable String username) {
		String fileName = profileRepo.savePhoto(username);

		return s3Util.createSignedPutUrl(fileName);
	}

	@GetMapping(value = "/user/photo/{username}")
	public String getPhoto(@PathVariable String username) {
		String fileName = profileRepo.getPhoto(username);

		return s3Util.createSignedGetUrl(fileName);
	}

	/// modified after 1st deploy
	@PostMapping(value = "/user/in", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody Login login) {
		String username = login.getUsername();
		String password = login.getPassword();

		try {
			if (activatedProfileRepo.isActivated(username)) {
				if (profileRepo.selectByUsername(username) == null) {
					return "User not found";
				} else {
					Profile profile = profileRepo.selectByUsername(username);

					if (aes.decrypt(profile.getPassword()).equals(password)) {
						return "User found";
					} else {
						return "Password Invalid";
					}
				}
			} else {
				return "User not activated";
			}
		} catch (Exception e) {
			return "User not found";
		}

	}

	@GetMapping(value = "/user/out/{username}")
	public boolean logout(@PathVariable String username) {
		return true;
	}

	@GetMapping(value = "/user/forgot/{emailOrUsername}")
	public boolean updatePassword(@PathVariable("emailOrUsername") String emailOrUsername) {
		String newPassword = validator.generatePassword();
		if (profileRepo.exists(emailOrUsername)) {
			if (validator.isEmail(emailOrUsername)) {
				Profile user = profileRepo.selectByEmail(emailOrUsername);
				user.setPassword(aes.encrypt(newPassword));
				profileRepo.update(user);

				emailContent("password", user, newPassword, "New Password", "");

			} else {
				Profile user = profileRepo.selectByUsername(emailOrUsername);
				user.setPassword(aes.encrypt(newPassword));
				profileRepo.update(user);

				emailContent("password", user, newPassword, "New Password", "");
			}
			return true;
		}
		return false;
	}

	public void emailContent(String template, Profile user, String newPassword, String subject, String objectKey) {
		EmailContentMaker emailContentMaker = new EmailContentMaker(newPassword, user.getFirstName(), template,
				new Date(), objectKey);

		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) {
				try {
					MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					mimeMsgHelperObj.setTo(user.getEmail());
					mimeMsgHelperObj.setFrom(sender); // System.getenv("em_username")
					mimeMsgHelperObj.setText(emailContentMaker.build(), true);
					mimeMsgHelperObj.setSubject(subject);
				} catch (MessagingException | TemplateException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
