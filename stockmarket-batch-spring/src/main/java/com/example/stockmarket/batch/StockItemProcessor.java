package com.example.stockmarket.batch;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.batch.item.ItemProcessor;

import com.example.stockmarket.entity.Stock;

public class StockItemProcessor implements ItemProcessor<Stock, Stock> {

	@Override
	public Stock process(Stock stock) throws Exception {
		stock.setPrice(stock.getPrice() * (1 + (ThreadLocalRandom.current().nextDouble() - 0.5) / 10.));
		return stock;
	}

}
