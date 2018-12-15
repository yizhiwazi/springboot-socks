package com.hehe.web;

import com.hehe.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    private List<User> userList = new ArrayList<>();

    {
        userList.add(new User("1", "socks", "123456", new Date()));
        userList.add(new User("2", "admin", "111111", new Date()));
        userList.add(new User("3", "jacks", "222222", null));
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("user/user", "userList", userList);
    }

    @GetMapping("/user/{userId}")
    public ModelAndView userForm(@PathVariable String userId) {
        return new ModelAndView("user/userForm", "user", userList.get(0));
    }

}
