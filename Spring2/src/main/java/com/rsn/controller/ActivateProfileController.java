package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.rsn.entity.Comments;
import com.rsn.repository.ActivatedProfileRepo;


/**
 * @author Gabriel Ferrer.
 * Is a Controller that has an endpoint for activating a user.
 * 
 */
@RestController
@CrossOrigin
public class ActivateProfileController {
	@Autowired
	private ActivatedProfileRepo activatedProfileRepo;
	
	@CrossOrigin
	@GetMapping(value = "/user/activate/{activeId}")
	public RedirectView activate(@PathVariable("activeId") String activeId) {
		System.out.println(activeId);
		activatedProfileRepo.activate(activeId);
		return new RedirectView("http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsng/");
	}
}
