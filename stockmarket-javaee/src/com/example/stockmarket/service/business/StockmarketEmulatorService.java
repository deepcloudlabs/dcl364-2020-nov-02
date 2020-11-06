package com.example.stockmarket.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.stockmarket.service.StockService;

@Stateless
public class StockmarketEmulatorService {
	@Inject private StockService stockService;
	//@Schedule(second="*/5", hour="*", minute = "*",persistent = false)
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
