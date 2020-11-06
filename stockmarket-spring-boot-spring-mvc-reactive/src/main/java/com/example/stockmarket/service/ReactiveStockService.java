package com.example.stockmarket.service;

import com.example.stockmarket.document.Stock;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveStockService {

	Mono<Stock> findStock(String symbol);

	Flux<Stock> findAll(int page, int size);

	Mono<Stock> add(Stock stock);

	Mono<Stock> update(Stock stock);

	void delete(String symbol);

}
