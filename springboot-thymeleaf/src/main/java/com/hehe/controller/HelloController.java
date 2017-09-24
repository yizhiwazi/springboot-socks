package com.hehe.controller;

import com.hehe.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/")
    public String go (){
        return "redirect:index";
    }

    @GetMapping("/index")
    public String index (){
        return "index";
    }

    @GetMapping("/register")
    public String reg (Model model){
        model.addAttribute("user",new User("S1","Kumamon","123456"));
        return "register";
    }

}
