package com.example.stockmarket.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockmarketEmulatorService {
	@Autowired
	private StockService stockService;
	@Autowired
	private ObjectMapper mapper; // Jackson -> JSON-B

	public String howToUserObjectMapper() throws JsonProcessingException {
		var stock = new Stock("orcl", "Oracle incorporated", "oracle inc.", 100.42);
		return mapper.writeValueAsString(stock);
	}

	@Scheduled(fixedRate = 5_000)
	public void updateStockPricesRandomly() {
		stockService.findAll(0, 25).thenAcceptAsync(stocks -> stocks.forEach(stock -> {
			double oldPrice = stock.getPrice();
			double changeRatio = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
			var newPrice = oldPrice * (1. + changeRatio);
			stock.setPrice(newPrice);
			stockService.update(stock);
		}));
	}
}
