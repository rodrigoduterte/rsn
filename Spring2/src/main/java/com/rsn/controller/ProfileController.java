package com.rsn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Profile> getAllProfiles(){
		return profileRepo.selectAll();
	}
	
	@GetMapping(value="/user/all")    //  /user/all?n=
	public List<Profile> getAllProfilesByFirstname(@RequestParam("n") String name){
		return profileRepo.selectAllByName(name);
	}
	
	@PostMapping(value="/user/new")
	public void insert(@RequestBody Profile user) {
		profileRepo.insert(user);
	}
	
	@PostMapping(value="/user/in")
	public void login(@RequestBody Profile user) {
		//return !profileRepo.insert(user).equals("");
	}
	
	@PostMapping(value="/user/out")
	public void logout(@RequestBody Profile user) {
		//return !profileRepo.insert(user).equals("");
	}
	
	
}

