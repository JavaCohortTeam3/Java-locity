package com.javalocity.javalocity.bean;

import jakarta.persistence.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 100, unique = true)
    private String username;

    @Column (nullable = false, length = 100, unique = true)
    private String email;

    @Column (nullable = false, length = 100)
    private String password;

    @Lob
    @Column (nullable = true, length = 50000, columnDefinition = "BLOB")
    private byte [] profile_img;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Post> posts;

//    public List<Post> getPosts() {
//        return posts;
//    }

//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(byte [] profile_img) {
        this.profile_img = profile_img;
    }

    public User(){}

    public User(User copy){
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String username, String email, String password, byte [] profile_img) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile_img = profile_img;
    }

    @Transient
    public String getPhotosImagePath() {
        if (profile_img == null || id == null) return null;

        return "/user-photos/" + id + "/" + profile_img;
    }

//    public User(Long id, String username, String email, String password, List<Post> posts) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.posts = posts;
//    }

//    public User(String username, String email, String password, List<Post> posts) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.posts = posts;
//    }
}

