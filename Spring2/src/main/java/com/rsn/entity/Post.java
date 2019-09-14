package com.rsn.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author vorga
 */
@Entity
@Table(name="POST")
public class Post {

    @Id
    @GeneratedValue
    private Long post_id;

    //@Basic(optional = false)
    private Date date;

    //@Basic
    private String photo;

    //@Basic(optional = false)
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    public Post() {
    }

    public Post(Date date, String photo, String body, Profile profile) {
		super();
		this.date = date;
		this.photo = photo;
		this.body = body;
		this.profile = profile;
	}



	public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", date=" + date + ", photo=" + photo + ", body=" + body + ", profile="
				+ profile + "]";
	}
    
    

}