package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return "Hello there from the Sirius cohort!";
//    }

    @RequestMapping(path= "/hello/{name}/and/{age}", method = RequestMethod.GET)
    @ResponseBody
    public String helloNameAge(@PathVariable String name, @PathVariable int age){
        return "Hey user thanks for letting me know your name is " + name + " and that you are " + age + " years old!";
    }

    @GetMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("name", "everybody");
        return "hello";
    }

    @RequestMapping(path= "/hello/{name}", method = RequestMethod.GET)
    public String helloNameAge(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
