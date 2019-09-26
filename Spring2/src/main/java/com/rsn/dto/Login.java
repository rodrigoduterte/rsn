package com.rsn.dto;

/**
 * @author Gabriel Ferrer.
 * A non persisting model for taking json properties from the body of GET /user/in request 
 * 
 */
public class Login {
	private String username;
	private String password;
	
	public Login() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
