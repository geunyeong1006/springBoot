package com.cyberone.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cyberone.demo.repository")
public class AndroidApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AndroidApplication.class, args);
	}
}
