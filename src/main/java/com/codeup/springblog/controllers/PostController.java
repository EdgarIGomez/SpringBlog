package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/post")
    public String post(Model model){
        ArrayList<Post> posts = new ArrayList<>();

        Post post1 = new Post("Title1", "This is the first post");
        Post post2 = new Post("Title2", "This is the second post");
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @RequestMapping(path = "/post/{id}")
    public String postById(@PathVariable int id, Model model){
        Post post = new Post("New Post", "This is the individual post page");
        model.addAttribute("post", post);
      return "posts/show";
    }

    @GetMapping( "/post/create")
    @ResponseBody
    public String createPost(){
        return "You will be viewing the form to make a post here!";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String postCreatePost(){
        return "Create a new  post here!";
    }



}
