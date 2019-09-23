package com.rsn.dto;

/**
 * @author Gabriel Ferrer.
 * A non persisting model for taking json properties from the body of POST /post/new/{username} request 
 * 
 */
public class PostBody {
	private String body;

	public PostBody(String body) {
		super();
		this.body = body;
	}

	public PostBody() {
		super();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
