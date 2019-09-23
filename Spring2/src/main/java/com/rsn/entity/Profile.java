package com.rsn.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsn.dto.EditProfile;
import com.rsn.service.*;

/**
 * @author vorga
 */
@Entity
@Table(name="PROFILE")
public class Profile { 
	
    @Id
    @GeneratedValue(generator = "user_seq_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq_id", sequenceName = "user_seq_id")
    private int user_id;

    @Basic
    @Column(unique = true, nullable = false)
    private String username;

    // needs to be unique
    @Basic
    @Column(unique = true, nullable = false)
    private String email;

    @Basic
    @Column(nullable = false)
    private String firstName;

    @Basic
    private String middleName;

    @Basic
    @Column(nullable = false)
    private String lastName;

    
    @Column(nullable = false)
    @Basic
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dob;

    @Basic
    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Basic
    private String favoriteColor;

    @Basic
    private String city;

    @Basic
    private String relationshipStatus;

    @Basic
    @Column(nullable = false)
    private String gender;

    @Basic
    @Column(nullable = false)
    private String bio;

    @Basic
    private String photo;

    @Basic
    private String occupation;

    public Profile() {
    }
    
    public Profile(String username, String email, String firstName, 
    		String middleName, String lastName, Timestamp dob,
			String password, String favoriteColor, String city, 
			String relationshipStatus, String gender, String bio,
			String photo, String occupation) throws ParseException {

		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.password = password;
		this.favoriteColor = favoriteColor;
		this.city = city;
		this.relationshipStatus = relationshipStatus;
		this.gender = gender;
		this.bio = bio;
		this.photo = photo;
		this.occupation = occupation;
	}

	public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setProfile(EditProfile ep) {
    	this.username = ep.getUsername();
    	System.out.println(ep.getUsername());
    	System.out.println(ep.getFirstName());
        this.email = ep.getEmail();
        this.firstName = ep.getFirstName();
        System.out.println(ep.getUsername());
        this.middleName = ep.getMiddleName();
        this.lastName = ep.getLastName();
        this.dob = ep.getDob();
        this.favoriteColor = ep.getFavoriteColor();
        this.city = ep.getCity();
        this.relationshipStatus = ep.getRelationshipStatus();
        this.gender = ep.getGender();
        this.bio = ep.getBio();
        this.occupation = ep.getOccupation();
    }
    
    
	@Override
	public String toString() {
		return "Profile [user_id=" + user_id + ", username=" + username + ", email=" + email + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", dob=" + dob + ", password="
				+ password + ", favoriteColor=" + favoriteColor + ", city=" + city + ", relationshipStatus="
				+ relationshipStatus + ", gender=" + gender + ", bio=" + bio + ", photo=" + photo + ", occupation="
				+ occupation + "]";
	}

}