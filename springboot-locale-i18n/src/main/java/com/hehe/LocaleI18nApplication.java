package com.hehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class LocaleI18nApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocaleI18nApplication.class, args);
    }

    /**
     * 登陆页面
     */
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("user/login");
    }
}
