package com.rsn.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rsn.service.BeanUtil;
import com.rsn.service.S3Util;

/**
 * 
 * @author vorga
 * Used to make profile inside Posts smaller 
 */
public class ProfileView {
	private String username;
	private String firstName;
	private String lastName;
	private String occupation;
	private String photo;
	
	private S3Util s3Util = BeanUtil.getBean(S3Util.class);
	
	public ProfileView() {}
	public ProfileView(String username, String firstName, String lastName, String occupation, String photo) {
		System.out.println(this.s3Util);
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.photo = s3Util.createSignedGetUrl(photo);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = s3Util.createSignedGetUrl(photo);
	}
}
