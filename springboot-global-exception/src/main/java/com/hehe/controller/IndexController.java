package com.hehe.controller;

import com.hehe.common.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {


    @RequestMapping("/")
    public String index() throws Exception {
        ExceptionUtils.randomException();
        return "Hello World!!";
    }

}
