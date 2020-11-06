package com.example.stockmarket.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.stockmarket.document.Stock;

import reactor.core.publisher.Flux;

public interface StockRepository extends ReactiveMongoRepository<Stock, String> {
	Flux<Stock> findByCompany(String company);

	@Query("{}")
	Flux<Stock> findAll(Pageable pageable);
}
