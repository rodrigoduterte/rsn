package com.rsn.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author vorga
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long username;

    @Basic
    @Column(nullable = false)
    private String firstname;

    @Basic
    private String middlename;

    @Basic
    @Column(nullable = false)
    private String lastname;

    @Basic
    @Column(nullable = false)
    private String dob;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    private String favorite_color;

    @Basic
    private String city;

    @Basic
    private String marital_status;

    @Basic
    @Column(nullable = false)
    private String gender;

    @Basic
    @Column(nullable = false)
    private String bio;

    @Basic
    private String picture;

    @Basic
    private String occupation;

    @OneToMany
    private List<Post> posts;

    @OneToMany
    private List<Likes> likeses;

    @OneToMany
    private List<User> friends;

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<String> getFavorite_color() {
        return Optional.ofNullable(favorite_color);
    }

    public void setFavorite_color(String favorite_color) {
        this.favorite_color = favorite_color;
    }

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Optional<String> getMarital_status() {
        return Optional.ofNullable(marital_status);
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Optional<String> getPicture() {
        return Optional.ofNullable(picture);
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Post> getPosts() {
        if (posts == null) {
            posts = new ArrayList<Post>();
        }
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        getPosts().add(post);
    }

    public void removePost(Post post) {
        getPosts().remove(post);
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

    public List<User> getFriends() {
        if (friends == null) {
            friends = new ArrayList<User>();
        }
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        getFriends().add(friend);
    }

    public void removeFriend(User friend) {
        getFriends().remove(friend);
    }

}