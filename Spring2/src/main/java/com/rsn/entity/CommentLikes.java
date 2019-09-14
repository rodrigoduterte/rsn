package com.rsn.entity;

import javax.persistence.CascadeType;
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
public class CommentLikes {

    @Id
    @GeneratedValue(generator = "comlike_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comlike_id_seq", sequenceName = "comlike_id_seq")
    private Long like_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    private Comments comments;

    public CommentLikes() {
    }

    public CommentLikes(Profile profile, Comments comments) {
		super();
		this.profile = profile;
		this.comments = comments;
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

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

}