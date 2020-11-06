package com.example.stockmarket.processor;

import java.util.concurrent.ThreadLocalRandom;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named("stockItemProcessor")
public class StockItemProcessor implements ItemProcessor {
	private static final ThreadLocalRandom random = ThreadLocalRandom.current();

	@Override
	public Object processItem(Object o) throws Exception {
		if (o instanceof Stock) {
			Stock stock = (Stock) o;
			stock.setPrice(stock.getPrice() * (1. + (random.nextDouble() - 0.5) * 0.01));
		}
		return o;
	}

}
