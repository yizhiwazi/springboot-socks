package com.hehe.controller;

import com.hehe.entity.User;
import com.hehe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public List<User> get() {

        userRepository.save(new User("1", "", null));

        return userRepository.findAll();
    }
}
