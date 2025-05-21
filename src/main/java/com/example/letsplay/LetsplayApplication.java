package com.example.letsplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.example.letsplay.user.repository", "com.example.letsplay.product.repository"})
public class LetsplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsplayApplication.class, args);
	}

}
