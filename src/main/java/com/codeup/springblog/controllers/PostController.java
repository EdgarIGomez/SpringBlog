package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String post(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/post/{id}")
    public String postById(@PathVariable long id, Model model){
        Post postFromId = postDao.getById(id);
        model.addAttribute("post", postFromId);
        return "posts/show";
    }

    @GetMapping( "/post/create")
    public String createPost(){
        return "posts/create";
    }

    @PostMapping("/post/create")
    public String postCreatePost(WebRequest request){
        String title = request.getParameter("postTitle");
        String body = request.getParameter("postBody");
        User user = userDao.getById(1L);
        Post newPost = new Post(title, body, user);
        postDao.save(newPost);
        return "redirect:/posts";
    }



}
