package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping(path = "/posts/{id}")
    public String postById(@PathVariable long id, Model model){
        Post postFromId = postDao.getById(id);
        model.addAttribute("post", postFromId);
        return "posts/show";
    }

    @GetMapping( "/posts/create")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("action", "create");
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreatePost(@ModelAttribute Post post){

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        postDao.save(post);
        String emailBody = "New post has been created. " + "Title: " + post.getTitle();
        emailService.prepareAndSend(post, "New Post", emailBody);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        String action = id + "/edit";
        model.addAttribute("action", action);
        return "posts/create";
    }

    @PostMapping("/posts/{id}/edit")
    public String saveEditedPost(@ModelAttribute Post post, @PathVariable long id){
        post.setId(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id, Model model){
        Post post = postDao.getById(id);
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}
