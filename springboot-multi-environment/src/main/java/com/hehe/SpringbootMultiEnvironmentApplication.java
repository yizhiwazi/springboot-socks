package com.hehe;

import com.hehe.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
public class SpringbootMultiEnvironmentApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyConfig myConfig;

	@Value("${author.my-uuid}")
	String id;

	@RequestMapping("/")
	public List index (){
		return myConfig.getWebsites();
	}

	@RequestMapping("/id")
	public String getIds (){
		return id;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultiEnvironmentApplication.class, args);
	}
}
