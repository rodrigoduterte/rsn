package com.rsn.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author vorga
 */
@Entity
public class Comments {

    @Id
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq")
    private Long comment_id;

    @Basic
    @Column(nullable = false)
    private Date comment_date;

    @Basic
    @Column(nullable = false)
    private String post_body;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    private Posts posts;

    public Comments() {
    }

    
    
    public Comments(Date comment_date, String post_body, Profile profile, Posts posts) {
		super();
		this.comment_date = comment_date;
		this.post_body = post_body;
		this.profile = profile;
		this.posts = posts;
	}



	public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

}