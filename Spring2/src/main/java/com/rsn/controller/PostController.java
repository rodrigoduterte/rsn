package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.entity.Post;
import com.rsn.repository.PostRepo;
import com.rsn.service.S3Service;

@RestController
@CrossOrigin
public class PostController {

	@Autowired
	private PostRepo postRepo;

	@PostMapping(value = "/post/new")
	public void insert(Post post) {
		postRepo.insert(post);
	}

}
