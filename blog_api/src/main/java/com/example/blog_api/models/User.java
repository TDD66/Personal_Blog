package com.example.blog_api.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column
    private String Password;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;

    public User(long id, String name, String password, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        Password = password;
        this.blogs = blogs;
    }

    public User() {
    }

//    GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    // ADDITIONAL METHODS

    public void addBlog(Blog blog){
        this.blogs.add(blog);
    }

    public void removeBlog(Blog blog){
        this.blogs.remove(blog);
    }
}