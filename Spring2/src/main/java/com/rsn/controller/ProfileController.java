package com.rsn.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.entity.Profile;
import com.rsn.repository.*;

@RestController
@CrossOrigin
public class ProfileController {

	@Autowired
	private ProfileRepo profileRepo;
	
	@GetMapping(value="/user/{username}")
	public Profile getUser(@PathVariable("username") String username) {
		return profileRepo.selectByUsername(username);
	}
	
	
	@GetMapping(value="/user/all")
	public List<Profile> getAllProfiles(@RequestParam("n") String name){
		if (name == null || name.equals("")) {
			return profileRepo.selectAll();
		} else {
			return profileRepo.selectAllByName(name);
		}
		
	}
	
	@RequestMapping(value = "/user/new", 
			method = RequestMethod.POST,consumes="application/x-www-form-urlencoded",   //"application/json
			headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody boolean insert(@ModelAttribute Profile user) {
		if (profileRepo.insert(user) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping(value="/user/in")
	public @ResponseBody Profile login(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if ( profileRepo.selectByUsername(username) == null) {
				return null;
			} else {
				Profile profile = profileRepo.selectByUsername(username);
				if (profile.getPassword().equals(password)) {
					return profile;
				} else {
					return null;
				}
			}
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@PostMapping(value="/user/out")
	public void logout(@RequestBody Profile user) {
		//return !profileRepo.insert(user).equals("");
	}
	
	
}

