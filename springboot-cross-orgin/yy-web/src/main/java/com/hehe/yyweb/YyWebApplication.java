package com.hehe.yyweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class YyWebApplication {

	@RequestMapping("/hello")
	public String index( ){
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(YyWebApplication.class);
		Map<String,Object> map = new HashMap<>();
		map.put("server.port",8090);
		app.setDefaultProperties(map);
		app.run(args);
	}
}
