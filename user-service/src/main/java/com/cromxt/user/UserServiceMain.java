package com.cromxt.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class UserServiceMain {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceMain.class, args);
	}
}
