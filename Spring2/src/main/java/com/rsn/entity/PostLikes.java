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
    @GeneratedValue(generator = "like_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "like_id_seq", sequenceName = "like_id_seq")
    private Long like_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    public PostLikes() {
    }
    
    public PostLikes(Profile profile, Post post) {
		super();
		this.profile = profile;
		this.post = post;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}