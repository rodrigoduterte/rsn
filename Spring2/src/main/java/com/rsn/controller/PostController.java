package com.rsn.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.HttpMethod;
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
		
		String signedUrl = s3Util.createSignedPutUrl(fileName);
		
		if (!signedUrl.equals("")) {
			Posts post = new Posts(new Date(), fileName, body, profile);
			postRepo.insert(post);
			return signedUrl;
		}
		
		// returns placeholder image
		return "";
	}

	@GetMapping(value = "/post/all")
	public List<Posts> getAll() {
		List<Posts> posts = postRepo.selectAll();
		for(Posts post: posts) {

			String fileName = post.getPhoto();
			String usernameOfPost = post.getProfile().getUsername();
			
			String signedUrl = s3Util.createSignedGetUrl(fileName);
			post.setPhoto(signedUrl);
		}
		
		return posts;
	}
	
	@GetMapping(value = "/post/{username}")
	public List<Posts> getPostsByUsername(@PathVariable("username") String username) {
		int profileId = profileRepo.selectByUsername(username).getUser_id();
		
		List<Posts> posts = postRepo.selectByProfileId(profileId);
		for(Posts post: posts) {

			String fileName = post.getPhoto();
			String usernameOfPost = post.getProfile().getUsername();
			
			String signedUrl = s3Util.createSignedGetUrl(fileName);
			post.setPhoto(signedUrl);
		}
		
		return posts;
	}
	
	
	@GetMapping(value = "/post/after/{postId}")
	public List<Posts> getLatest(@PathVariable(name="postId") Long postId) {
		List<Posts> posts = postRepo.selectLatestPosts(postId);
		for(Posts post: posts) {

			String fileName = post.getPhoto();
			String usernameOfPost = post.getProfile().getUsername();
			
			String signedUrl = s3Util.createSignedGetUrl(fileName);
			post.setPhoto(signedUrl);
		}
		
		return posts;
	}
}
