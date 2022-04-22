package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/post/create")
    public String postCreatePost(@ModelAttribute Post post){

        post.setUser(userDao.getById(1L));
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/post/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "posts/edit-post";
    }

    @PostMapping("/post/{id}/edit")
    public String saveEditedPost(@ModelAttribute Post post, @PathVariable long id){
        post.setId(id);
        post.setUser(userDao.getById(1L));
        postDao.save(post);
        return "redirect:/posts";
    }


}
