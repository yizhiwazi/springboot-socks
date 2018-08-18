package com.hehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {

    /**
     * 登陆界面
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 聊天界面
     */
    @GetMapping("/index")
    public ModelAndView index(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }
        return new ModelAndView("/chat", "username", username);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }
}
