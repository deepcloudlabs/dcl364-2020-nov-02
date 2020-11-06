package com.example.stockmarket.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.stockmarket.service.StockService;

@Service
public class StockmarketEmulatorService {
	@Inject private StockService stockService;
	@Scheduled(fixedRate = 5_000)
	public void updateStockPricesRandomly() {
		stockService.findAll(0, 25)
		            .forEach( stock -> {
		            	double oldPrice = stock.getPrice();
						double changeRatio = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
						var newPrice = oldPrice * ( 1. + changeRatio);
						stock.setPrice(newPrice );
						stockService.update(stock);
		            });
	}
}
