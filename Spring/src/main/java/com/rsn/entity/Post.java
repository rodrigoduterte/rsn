package com.rsn.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author vorga
 */

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long post_id;

    @Basic
    private String title;

    @Basic
    private String body;

    @OneToMany
    private List<Comment> comments;

    @OneToMany
    private List<Likes> likeses;

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        if (comments == null) {
            comments = new ArrayList<Comment>();
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
    }

    public void removeComment(Comment comment) {
        getComments().remove(comment);
    }

    public List<Likes> getLikeses() {
        if (likeses == null) {
            likeses = new ArrayList<Likes>();
        }
        return likeses;
    }

    public void setLikeses(List<Likes> likeses) {
        this.likeses = likeses;
    }

    public void addLikese(Likes likese) {
        getLikeses().add(likese);
    }

    public void removeLikese(Likes likese) {
        getLikeses().remove(likese);
    }

}