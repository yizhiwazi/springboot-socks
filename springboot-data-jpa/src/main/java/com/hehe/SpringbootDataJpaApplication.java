package com.hehe;

import com.hehe.common.CommonRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hehe.repository", repositoryBaseClass = CommonRepositoryImpl.class)
public class SpringbootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDataJpaApplication.class, args);
	}
}
