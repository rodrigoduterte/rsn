package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.HttpMethod;
import com.rsn.service.S3Util;

/**
 * 
 * @author Gabriel Ferrer
 * Not an official part of controllers only for demo purposes
 */
@RestController
public class S3Controller {

	@Autowired
	private S3Util s3Util;
	
	@PutMapping("/file")
	public String createSignedPutUrl(@PathVariable String fileName) {
		// we would probably want a service layer where would go provide additional
		// logic to restrict who can get signed urls based off the filename
		
		return s3Util.createSignedUrl(fileName, HttpMethod.PUT);
	}

	@GetMapping("/file/{fileName}")
	public String createSignedGetUrl(@PathVariable String fileName) {
		// we would probably want a service layer where would go provide additional
		// logic to restrict who can get signed urls based off the filename
		return s3Util.createSignedUrl(fileName, HttpMethod.GET);
	}

}
