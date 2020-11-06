package com.example.stockmarket.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.service.StockService;

// http://localhost:8080/stockmarket/api/v1/stocks
@RestController
@RequestMapping("/stocks")
@RequestScope
public class StockRestController {
	@Autowired
	private StockService stockService;

	// http://localhost:8080/stockmarket/api/v1/stocks/orcl
	// http://localhost:8080/stockmarket/api/v1/stocks/ibm
	@GetMapping("/{symbol}")
	public Stock findBySymbol(@PathVariable String symbol) {
		return stockService.findStock(symbol);
	}

	// http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=10
	@GetMapping
	@Async
	public CompletableFuture<List<Stock>> findAll(@RequestParam(required = true, defaultValue = "0") int page,
			@RequestParam(required = true, defaultValue = "25") int size) {
		return stockService.findAll(page, size);
	}

	@PostMapping
	public Stock addStock(@RequestBody Stock stock) {
		return stockService.add(stock);
	}

	@PutMapping
	public Stock updateStock(@RequestBody Stock stock) {
		return stockService.update(stock);
	}

	@DeleteMapping("/{symbol}")
	public Stock deleteStock(@PathVariable String symbol) {
		return stockService.delete(symbol);
	}

}
