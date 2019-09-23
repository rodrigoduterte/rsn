package com.rsn.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rsn.dto.PostsResponse;
import com.rsn.entity.Posts;
import com.rsn.entity.Profile;
import com.rsn.repository.PostRepo;
import com.rsn.repository.ProfileRepo;
import com.rsn.service.S3Util;


/**
 * @author Gabriel Ferrer
 * Is a Controller that has endpoints for adding, updating and deleting posts 
 * 	REST Endpoints
 * 		PUT /post/photo/{username}					: Generates a signed S3 url that the client 
 * 														sends with the file content to S3
 * 		PUT /post/photo/{username}?posti=postId		: Generates a signed S3 url that the client 
 * 														sends with the file content to s3
 * 		GET /post/photo/{postId}					: Generates a signed S3 url that the client can view or open
 */
@RestController
@CrossOrigin
@SessionAttributes(value = "username")
@PropertySource("classpath:app.properties")
public class PhotoController {
	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private S3Util s3Util;
	
	
	/**
	 * Is a method responds with a PostResponse that is viewed as a JSON string in the client
	 * 
	 * 	Scenarios
	 * 	 1. PUT /post/photo/{username}?posti=   OR    PUT /post/photo/{username}
	 * 		- User clicks the upload button
	 *      - 
	 * 
	 * 	Sample Request URL
	 * 	 1. Request URL for	uploading and sending the photo only
	 * 		PUT /post/photo/{username}?posti=   OR    PUT /post/photo/{username}
	 *   1. No Request Body for	uploading and sending the photo only
	 *   
	 * 	 2. Request URL for sending the photo with the body
	 * 		/post/photo/{username}?posti=1  
	 * 	 2. No Request Body for sending the photo with the body
	 * 		
	 * 	Sample Response 
	 * 	 1. Send this response for request# 1   
	 	 * postId: the id of the newly generated post
		 * 	{
		 * 		postId: 1,
		 * 		postPhoto: "/presigned/URL/needed_by_S3_with_the_file_content"
		 * 	}
	 *	 2. Send this response for request# 2 	
		 * 	{
		 * 		postId: 1,
		 * 		postPhoto: "/presigned/URL/needed_by_S3_with_the_file_content"
		 * 	}
	 *  
	 *  Algorithm
	 *  1. 
	 *  
	 * @param username gets the username from the path
	 * @param posti gets the postId from the query parameter ?posti=postId          
	 */
	@PutMapping(value = "/post/photo/{username}", produces = "application/json")
	public PostsResponse createPhoto(@PathVariable String username,
			@RequestParam(name = "posti", defaultValue = "") String posti) {
		Long postId;
		
		if (posti.equals("")) {
			/// create new post
			String newPhoto = postRepo.createPhotoName();   /// create new photo name
			Profile profile = profileRepo.selectByUsername(username);   /// get profile
			/// create a new post with date when post happened, new photo name, profile retrieved
			postId = postRepo.insert( new Posts(new Date(), newPhoto, null, profile) );  
			
			/// create a presigned url for new photo
			String presignedURL = s3Util.createSignedPutUrl(newPhoto);
			/// send the created postId and image presigned url
			return new PostsResponse(postId, presignedURL);
		} else {
			/// update post with photo
			String newPhoto = postRepo.createPhotoName();  /// create new photo name
			Profile profile = profileRepo.selectByUsername(username);   /// get profile
			Posts post = postRepo.selectById( Long.parseLong(posti) );  /// Convert string to long, get post with postId
			
			/// if photo doesn't exists create a new photo reference
			if (post.getPhoto().equals("")) {
				post.setPhoto(newPhoto);   /// rename photo
				postRepo.update(post);     /// update photo of post
			}
			
			String presignedURL = s3Util.createSignedPutUrl(post.getPhoto());
			/// send the created postId and image presigned url
			return new PostsResponse(post.getPost_id(), presignedURL);
		}
	}
	
	/**
	 * Is a method responds with a PostResponse that is viewed as a JSON string in the client
	 * 
	 * 	Scenarios
	 * 	 1. GET /post/photo/{postId}
	 * 		- User gets the s3 location to the photo
	 * 
	 * 	Sample Request URL
	 * 	 1. Request URL for	uploading and sending the photo only
	 * 		PUT /post/photo/{username}?posti=   OR    PUT /post/photo/{username}
	 *   1. No Request Body for	uploading and sending the photo only
	 *   
	 * 	 2. Request URL for sending the photo with the body
	 * 		/post/photo/{username}?posti=1  
	 * 	 2. No Request Body for sending the photo with the body
	 * 		
	 * 	Sample Response 
	 * 	 1. Send this response for request# 1   
	 *      "/s3/file/location" 
	 *  
	 * @param username gets the username from the path
	 * @param posti gets the postId from the query parameter ?posti=postId          
	 */
	@GetMapping(value = "/post/photo/{postId}")
	public String getPhoto(@PathVariable String postId) {
		Long posti = Long.parseLong(postId);
		String fileName = postRepo.selectById(posti).getPhoto();
		return s3Util.createSignedGetUrl(fileName);
	}
}
