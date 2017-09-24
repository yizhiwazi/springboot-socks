package com.hehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootWarJspApplication extends SpringBootServletInitializer {//提供Servet支持

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootWarJspApplication.class);//指明应用
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWarJspApplication.class, args);
	}
}
