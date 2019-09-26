package com.rsn.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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

import com.rsn.dto.PostBody;
import com.rsn.dto.PostsResponse;
import com.rsn.entity.PostLikes;
import com.rsn.entity.Posts;
import com.rsn.entity.Profile;
import com.rsn.repository.PostLikesRepo;
import com.rsn.repository.PostRepo;
import com.rsn.repository.ProfileRepo;
import com.rsn.service.S3Util;

/**
 * @author Gabriel Ferrer
 * Is a Controller that has endpoints for adding, updating and deleting posts 
 * 	REST Endpoints
 * 		GET /post/all								: Returns all the posts of all users
 * 		GET /post/all/{username}					: Returns all the posts by a given username  
 * 		GET /post/after/{postId}					: Returns all the posts made after the given postId
 * 		POST /post/new/{username}					: Returns a new post for the given username
 *  	POST /post/new/{username}?posti=postId		: Updates a post for the given username and postId
 *  	POST /post/edit/{postId}					: Edits an existing post
 */
@RestController
@CrossOrigin
@PropertySource("classpath:app.properties")
public class PostController {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private PostLikesRepo postLikesRepo;
	
	@Autowired
	private S3Util s3Util;
	
	@CrossOrigin
	@PostMapping(value = "/post/edit/{postId}", consumes = "application/json")
	public String edit(@RequestBody PostBody body, @PathVariable("postId") String postId) {
		Long id = Long.parseLong(postId);
		
		try {
			Posts post = postRepo.selectById(id);
			post.setPost_body(body.getBody());
			postRepo.update(post);
		} catch (Exception e) {
			return "Not added";
		}
		return "Added";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/post/new/{username}", 
			method = RequestMethod.POST, consumes = "application/json",
			produces = "application/json") 
	public PostsResponse create(@RequestBody PostBody body, @PathVariable("username") String username
			,@RequestParam(name = "posti", defaultValue = "") String posti) {
		Long postId = null;
		
		if (posti.equals("")) {
			try {
			//// create new post
				Profile profile = profileRepo.selectByUsername(username);   /// get profile
				postId = postRepo.insert( new Posts(new Date(), null, body.getBody(), profile) );
			} catch(Exception e) {
				return new PostsResponse(null, "Post not added");
			}
		} else {
			//// update a new post
			postId = Long.parseLong(posti);
			Posts post = postRepo.selectById(postId);
			post.setPost_body(body.getBody());
			postRepo.update(post);
			return new PostsResponse(postId, "Post updated");
		}
		////
		return new PostsResponse(postId, "Post added");
	}

	@CrossOrigin
	@GetMapping(value = "/post/all")
	public List<Posts> getAll() {
		postLikesRepo.clear();
		profileRepo.clear();
		postRepo.clear();
		List<Posts> posts = postRepo.selectAll();
		for (Posts post : posts) {
			Profile profile = post.getProfile();

			String postSignedUrl = s3Util.createSignedGetUrl( post.getPhoto() );
			
			List<PostLikes> postLikes = postLikesRepo.selectPostLikesByPostId( post.getPost_id() );
			
			profile.setPhoto(  post.getProfile().getPhoto() );
			post.setLikes( postLikes );
			post.setPhoto( postSignedUrl );
			post.setProfileView( profile );
			
		}
		postLikesRepo.clear();
		profileRepo.clear();
		postRepo.clear();
		return posts;
	}

	@CrossOrigin
	@GetMapping(value = "/post/{username}")
	public List<Posts> getPostsByUsername(@PathVariable("username") String username) {
		int profileId = profileRepo.selectByUsername(username).getUser_id();

		List<Posts> posts = postRepo.selectByProfileId(profileId);
		for (Posts post : posts) {
			Profile profile = post.getProfile();

			String postSignedUrl = s3Util.createSignedGetUrl( post.getPhoto() );
			
			List<PostLikes> postLikes = postLikesRepo.selectPostLikesByPostId( post.getPost_id() );
			
			profile.setPhoto(  post.getProfile().getPhoto() );
			post.setLikes( postLikes );
			post.setPhoto( postSignedUrl );
			post.setProfileView( profile );
		}

		return posts;
	}

	@CrossOrigin
	@GetMapping(value = "/post/after/{postId}")
	public List<Posts> getLatest(@PathVariable(name = "postId") Long postId) {
		List<Posts> posts = postRepo.selectLatestPosts(postId);
		for (Posts post : posts) {
			Profile profile = post.getProfile();

			String postSignedUrl = s3Util.createSignedGetUrl( post.getPhoto() );
			
			List<PostLikes> postLikes = postLikesRepo.selectPostLikesByPostId( post.getPost_id() );
			
			profile.setPhoto(  post.getProfile().getPhoto() );
			post.setLikes( postLikes );
			post.setPhoto( postSignedUrl );
			post.setProfileView( profile );
		}

		return posts;
	}
	
	
}
