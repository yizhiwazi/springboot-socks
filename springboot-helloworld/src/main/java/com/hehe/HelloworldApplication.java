package com.hehe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  为了让小伙伴们对Boot有更直观的了解 可以先简单阅读这些说明。
 *
 * 一、SpringBoot 特性
   　1.座靠Spring （未来颠覆者）
   　2.内嵌Tomcat、Jetty （无需部署）
   　3.提供各种Starter （简化依赖）
     4.提供自动配置 （开发神器）
     5.告别XML　（妈妈再也不用担我写错配置了）

 * 二、快速学习-便携式入口
 * {@link SpringBootApplication }
 *
 *   {@link EnableAutoConfiguration 1.开启自动配置 } 例如在ClassPath看到MVC包则自动去初始化WEB环境
 *   {@link Configuration 2.标记配置类 }  例如制定方法为上下文提供Bean.
 *   {@link ComponentScan 3.开启组件扫描 } 例如@Controller,@Service等.
 *
 */

@SpringBootApplication
@Controller
public class HelloworldApplication {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args); //启动项目
    }


}
