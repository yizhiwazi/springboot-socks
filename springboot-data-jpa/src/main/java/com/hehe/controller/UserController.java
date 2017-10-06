package com.hehe.controller;

import com.hehe.entity.R;
import com.hehe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("list/{username}")
    public R listbyname(@PathVariable("username")  String username) {
        try {
            return R.isOk().data(userService.findByUsername(username));
        } catch (Exception e) {
            return R.isFail(e);
        }
    }

    @GetMapping("get/{userId}")
    public R get(@PathVariable("userId") String userId) {
        try {
            return R.isOk().data(userService.get(userId));
        } catch (Exception e) {
            return R.isFail(e);
        }
    }
}
