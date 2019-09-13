package com.rsn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author vorga
 */

@Entity
public class Likes {

    @Id
    @GeneratedValue
    private Long like_id;

    public Long getLike_id() {
        return like_id;
    }

    public void setLike_id(Long like_id) {
        this.like_id = like_id;
    }

}