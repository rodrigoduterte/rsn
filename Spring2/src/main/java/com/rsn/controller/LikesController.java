package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.entity.PostLikes;
import com.rsn.entity.Posts;
import com.rsn.entity.Profile;
import com.rsn.repository.CommentLikesRepo;
import com.rsn.repository.PostLikesRepo;
import com.rsn.repository.PostRepo;
import com.rsn.repository.ProfileRepo;

/**
 * @author Gabriel Ferrer.
 * Is a Controller that has endpoints for adding and deleting likes of posts or comments 
 * 	REST Endpoints
 * 		GET /like/new/{username}/{postId}			: creates a new post like
 * 		GET /like/update/{username}/{postId}		: converts a post like into unlike or like state
 * 		
 */
@RestController
@CrossOrigin
public class LikesController {

	@Autowired
	private PostLikesRepo postLikesRepo;
	
	@Autowired
	private CommentLikesRepo commentLikesRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ProfileRepo profileRepo;

	@CrossOrigin
	@GetMapping(value = "/like/create/{username}/{postId}")
	public String insertPostLike(@PathVariable String username, @PathVariable String postId) {
		postLikesRepo.clear();
		profileRepo.clear();
		postRepo.clear();
		Profile user = profileRepo.selectByUsername(username);
		Posts post = postRepo.selectById(  Long.parseLong(postId)  );
		PostLikes postLike = null;
		String liked = "";

		
		if ( postLikesRepo.exists(username, Long.parseLong(postId)) ) {
			postLike = postLikesRepo.selectSpecificLike(username, Long.parseLong(postId));
			liked = postLikesRepo.toggle(postLike);
		} else {
			postLike = new PostLikes(user, post, true);
			postLikesRepo.insert(postLike);
			liked = "Liked";
		}
		
		
		postLikesRepo.clear();
		profileRepo.clear();
		postRepo.clear();
		return "Post " + liked;
	}

}
