package com.rsn.dto;

/**
 * @author Gabriel Ferrer.
 * A non persisting model that will be sent as a response for PUT /post/photo/{username} request or
 * 	PUT /post/photo/{username}?posti=postId request
 * 
 */
public class PostsResponse {
	private Long postId;
    private String postPhoto;
    
    public PostsResponse() {}

	public PostsResponse(Long postId, String file) {
		super();
		this.postId = postId;
		this.postPhoto = file;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostPhoto() {
		return postPhoto;
	}

	public void setPostPhoto(String file) {
		this.postPhoto = file;
	}
}
