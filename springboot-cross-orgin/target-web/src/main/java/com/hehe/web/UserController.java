package com.hehe.web;

import com.hehe.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * 查询用户（要求前端传递用户对象）
     */
    @PostMapping("/user/findByObject")
    public User findByObject(User user) {
        user.setUsername("admin");
        return user;
    }

    /**
     * 查询用户（要求前端传递用户JSON）
     */
    @PostMapping("/user/findByJson")
    public User findByJson(@RequestBody User user) {
        user.setUsername("jack");
        return user;
    }
}
