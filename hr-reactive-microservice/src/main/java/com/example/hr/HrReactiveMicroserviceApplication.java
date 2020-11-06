package com.example.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableReactiveMongoRepositories // Reactive Repository
@EnableWebFlux // Reactive REST API
public class HrReactiveMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrReactiveMicroserviceApplication.class, args);
	}

}
