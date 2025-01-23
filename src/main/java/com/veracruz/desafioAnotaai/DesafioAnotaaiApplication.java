package com.veracruz.desafioAnotaai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DesafioAnotaaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioAnotaaiApplication.class, args);
	}
}
