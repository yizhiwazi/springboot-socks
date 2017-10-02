package com.hehe.controller;

import com.hehe.config.HerDataSource;
import com.hehe.config.MyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    MyDataSource myDataSource;

    @Autowired
    HerDataSource herDataSource;

    @Autowired
    Environment environment;

    @GetMapping("/")
    public List source(){
        return Arrays.asList(myDataSource,herDataSource);
    }

    @GetMapping("/env")
    public List env(){
        MyDataSource dataSource =  new MyDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        return Arrays.asList(new MyDataSource(),dataSource);
    }


}
