package com.example.stockmarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.example.stockmarket.document.Stock;
import com.example.stockmarket.service.ReactiveStockService;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableWebFlux
public class StockmarketSpringBootSpringMvcReactiveApplication implements ApplicationRunner{
	@Autowired
	private ReactiveStockService reactiveStockService;
	
	public static void main(String[] args) {
		SpringApplication.run(StockmarketSpringBootSpringMvcReactiveApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var orcl = new Stock("orcl", "oracle inc.", "oracle", 100.0);
		reactiveStockService.add(orcl ).block();
	}

}
