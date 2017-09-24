package com.hehe.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() throws Exception {
        if(Math.random()>0.6){
          throw new RuntimeException("发生异常");
        }
        return "Hello World!!";
    }

    @RequestMapping("/{name}")
    public String hello(@PathVariable String name) throws Exception {

        return "Hello " +name;
    }

}
