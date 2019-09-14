package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.entity.Comments;
import com.rsn.repository.CommentRepo;
import com.rsn.service.S3Service;

@RestController
@CrossOrigin
public class CommentController {

	@Autowired
	private CommentRepo commentRepo;

	@PostMapping(value = "/com/new")
	public void insert(@RequestBody Comments comment) {
		commentRepo.insert(comment);
	}

	@PostMapping(value = "/com/delete")
	public void delete(@RequestBody Comments comment) {
		commentRepo.delete(comment);
	}

}
