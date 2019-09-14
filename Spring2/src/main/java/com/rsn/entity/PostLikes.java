package com.rsn.entity;

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
@Table(name="POSTLIKES")
public class PostLikes {

    @Id
    @GeneratedValue(generator = "postlike_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "postlike_id_seq", sequenceName = "postlike_id_seq")
    private Long like_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    private Posts posts;

    public PostLikes() {
    }

    
    
    public PostLikes(Profile profile, Posts posts) {
		super();
		this.profile = profile;
		this.posts = posts;
	}



	public Long getLike_id() {
        return like_id;
    }

    public void setLike_id(Long like_id) {
        this.like_id = like_id;
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