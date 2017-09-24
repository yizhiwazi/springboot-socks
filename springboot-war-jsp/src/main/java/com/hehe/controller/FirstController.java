package com.hehe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String index (){
        return "index";
    }

    @GetMapping("/hehe")
    public String hehe (){
        return "hehe";
    }

}
