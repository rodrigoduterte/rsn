package com.rsn.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author vorga
 */
@Entity
@Table(name="COMMENT")
public class Comment {

    @Id
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq")
    private Long comment_id;

    //@Basic(optional = false)
    private Date date;

    //@Basic(optional = false)
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    public Comment() {
    }
    
    

    public Comment(Date date, String body, Profile profile, Post post) {
		super();
		this.date = date;
		this.body = body;
		this.profile = profile;
		this.post = post;
	}



	public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}