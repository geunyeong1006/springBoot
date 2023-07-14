package com.cyberone.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AndroidApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AndroidApplication.class, args);
	}
}
