package com.example.blog_api.controllers;

import com.example.blog_api.models.Blog;
import com.example.blog_api.models.NewBlogDTO;
import com.example.blog_api.models.UpdateBlogDTO;
import com.example.blog_api.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs(){
        List<Blog> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> addBlog(@RequestBody NewBlogDTO newBlogDTO){
        Blog newBlog = blogService.addBlog(newBlogDTO);
        return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        Optional<Blog> blog = blogService.getById(id);
        if(blog.isPresent()){
            return new ResponseEntity<>(blog.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody UpdateBlogDTO updateBlogDTO){
        Optional<Blog> updatedBlog = blogService.updateBlog(updateBlogDTO, id);
        if(updatedBlog.isPresent()){
            return new ResponseEntity<>(updatedBlog.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
