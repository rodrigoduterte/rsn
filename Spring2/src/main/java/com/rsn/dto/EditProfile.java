package com.rsn.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsn.service.TimestampDeserializer;

/**
 * @author Gabriel Ferrer.
 * A non persisting model for taking json properties from the body of GET /user/in request 
 * 
 */
public class EditProfile {
    private String username;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dob;
    private String favoriteColor;
    private String city;
    private String relationshipStatus;
    private String gender;
    private String bio;
    private String occupation;
    
    public EditProfile() {
		// TODO Auto-generated constructor stub
	}
    
	public EditProfile(String username, String email, String firstName, String middleName, String lastName,
			Timestamp dob, String favoriteColor, String city, String relationshipStatus, String gender, String bio, String occupation) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.favoriteColor = favoriteColor;
		this.city = city;
		this.relationshipStatus = relationshipStatus;
		this.gender = gender;
		this.bio = bio;
		this.occupation = occupation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
    
    
}
