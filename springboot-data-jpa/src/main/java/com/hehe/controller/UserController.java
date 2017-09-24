package com.hehe.controller;

import com.hehe.entity.R;
import com.hehe.entity.User;
import com.hehe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public R list() {
        try {
           return R.isOk().data(userService.list());
        } catch (Exception e) {
            return R.isFail(e);
        }

    }
/*
    @PostMapping("save")
    public R save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("update")
    public R update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("delete")
    public R delete(@RequestBody List<User> users) {
        return userService.delete(users);
    }*/


}
