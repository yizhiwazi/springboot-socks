package com.hehe.web;

import com.hehe.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 用户管理
 */
@RestController
public class UserController {

    /**
     * 打开主页
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("user/user");
        mv.addObject("user", new User("1", "admin", "123456", new Date()));
        return mv;
    }

    /**
     * 自动根据请求来判断返回用户JSON或XML
     */
    @GetMapping("/user")
    public User get() {
        return new User("1", "admin", "123456", new Date());
    }

    /**
     * 返回用户JSON
     */
    @GetMapping(value = "/user/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getJson() {
        return new User("1", "admin", "123456", new Date());
    }

    /**
     * 返回用户XML
     */
    @GetMapping(value = "/user/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public User getXml() {
        return new User("1", "admin", "123456", new Date());
    }

}
