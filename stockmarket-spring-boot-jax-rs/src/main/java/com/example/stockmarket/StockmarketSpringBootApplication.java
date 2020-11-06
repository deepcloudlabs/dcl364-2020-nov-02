package com.example.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// Java EE -> WAR (Application Code) -> 7KB
// Spring Boot (Application Code + Embedded Container )-> Fat JAR -> 45.7 MB
   // mvn clean install spring-boot:repackage
// Containerization (Docker) Dockerfile -> COPY WAR (
@SpringBootApplication
@EnableScheduling
public class StockmarketSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketSpringBootApplication.class, args);
	}

}
