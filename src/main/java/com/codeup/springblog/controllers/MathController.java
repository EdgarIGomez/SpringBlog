package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @RequestMapping(path = "/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable int num1, @PathVariable int num2){
        int total = num1 + num2;
        return "Adding numbers " + num1 + " and " + num2 + " equals " + total + "!";
    }

    @RequestMapping(path = "/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract(@PathVariable int num1, @PathVariable int num2){
        int total = num2 - num1;
        return "Subtracting  number " + num1 + " from " + num2 + " equals " + total + "!";
    }

    @RequestMapping(path = "/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply(@PathVariable int num1, @PathVariable int num2){
        int total = num1 * num2;
        return "Multiplying numbers " + num1 + " and " + num2 + " equals " + total + "!";
    }

    @RequestMapping(path = "/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide(@PathVariable int num1, @PathVariable int num2){
        int total = num1 / num2;
        return "Dividing number " + num1 + " by " + num2 + " equals " + total + "!";
    }




}
