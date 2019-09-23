package com.rsn.entity;

import java.util.Date;
import java.util.Optional;
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
public class Posts {

    @Id
    @GeneratedValue(generator = "post_seq_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "post_seq_id", sequenceName = "post_seq_id")
    private Long post_id;

    @Basic
    @Column(nullable = false)
    private Date post_date;

    @Basic
    private String photo;

    @Basic
    private String post_body;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    public Posts() {
    }

    public Posts(Date post_date, String photo, String post_body, Profile profile) {
		super();
		this.post_date = post_date;
		this.photo = photo;
		this.post_body = post_body;
		this.profile = profile;
	}



	public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

}