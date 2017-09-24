package com.hehe.controller;

import com.hehe.domain.User;
import com.hehe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    @ResponseBody
    public List<User> list(User user){
        return userService.list(user);
    }

}
