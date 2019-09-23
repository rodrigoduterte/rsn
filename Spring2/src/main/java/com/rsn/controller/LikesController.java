package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.entity.PostLikes;
import com.rsn.repository.PostLikesRepo;

@RestController
@CrossOrigin
public class LikesController {

	@Autowired
	private PostLikesRepo postLikesRepo;

	@PostMapping(value = "/like/new")

	public void insert(@RequestBody PostLikes postLike) {
		postLikesRepo.insert(postLike);

	}

	@PostMapping(value = "/like/delete")
	public void delete(@RequestBody PostLikes postLike) {
		postLikesRepo.delete(postLike);
	}

}
