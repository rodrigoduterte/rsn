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
public class Comment {

    @Id
    @GeneratedValue
    private Long comment_id;

    @Basic
    private String body;

    @OneToMany
    private List<Likes> likeses;

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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