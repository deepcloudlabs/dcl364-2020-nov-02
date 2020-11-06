package com.example.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockmarket.document.Stock;
import com.example.stockmarket.service.ReactiveStockService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// http://localhost:4040/
@RestController
@RequestMapping("/stocks")
public class StockRestController {
	@Autowired
	private ReactiveStockService stockService;

	// http://localhost:4040/stocks/orcl
	// http://localhost:4040/stocks/ibm
	@GetMapping("/{symbol}")
	public Mono<Stock> findBySymbol(@PathVariable String symbol) {
		return stockService.findStock(symbol);
	}

	// http://localhost:4040/stocks?page=0&size=10
	@GetMapping
	public Flux<Stock> findAll(@RequestParam(required = true, defaultValue = "0") int page,
			@RequestParam(required = true, defaultValue = "25") int size) {
		return stockService.findAll(page, size);
	}

	@PostMapping
	public Mono<Stock> addStock(@RequestBody Stock stock) {
		return stockService.add(stock);
	}

	@PutMapping
	public Mono<Stock> updateStock(@RequestBody Stock stock) {
		return stockService.update(stock);
	}

	@DeleteMapping("/{symbol}")
	public void deleteStock(@PathVariable String symbol) {
		stockService.delete(symbol);
	}

}
