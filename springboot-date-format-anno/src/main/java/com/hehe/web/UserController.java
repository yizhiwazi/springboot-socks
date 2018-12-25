package com.hehe.web;

import com.hehe.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    /**
     * 查询用户信息
     */
    @GetMapping("/")
    public User get() {
        return new User("1", "socks", "123456", new Date(), "GMT");
    }

}
