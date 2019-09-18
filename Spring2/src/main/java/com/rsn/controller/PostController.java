package com.rsn.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.rsn.entity.PostLikes;
import com.rsn.entity.Posts;
import com.rsn.entity.Profile;
import com.rsn.repository.PostRepo;
import com.rsn.repository.ProfileRepo;
import com.rsn.service.S3Util;

@RestController
@CrossOrigin
public class PostController {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ProfileRepo profileRepo;
	
	@Autowired
	private S3Util s3Util;
	

	@PostMapping(value = "/post/new")
	//public String insert(@RequestBody Posts post) {
	public String insert(@RequestParam("post_body") String body, 
			@RequestParam("username") String username) {
		Profile profile = profileRepo.selectByUsername(username);
		
		String fileName = postRepo.createPhotoName();
		
		String signedUrl = s3Util.createSignedUrl(fileName, HttpMethod.PUT);
		
		if (!signedUrl.equals("")) {
			Posts post = new Posts(new Date(), fileName, body, profile);
			postRepo.insert(post);
			return signedUrl;
		}
		
		// returns placeholder image
		return "https://cdn.pixabay.com/photo/2015/03/04/22/35/head-659651_960_720.png";
	}

	
	
}
