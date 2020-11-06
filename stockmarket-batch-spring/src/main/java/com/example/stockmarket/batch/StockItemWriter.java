package com.example.stockmarket.batch;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.batch.item.ItemWriter;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

public class StockItemWriter implements ItemWriter<Stock>, Closeable {
	private StockRepository stockRepository;

	public StockItemWriter(StockRepository stockRepository) {
		super();
		this.stockRepository = stockRepository;
	}

	@Override
	@PreDestroy
	public void close() throws IOException {
		stockRepository.flush();
	}

	@Override
	public void write(List<? extends Stock> items) throws Exception {
		items.forEach(stockRepository::save);
  	}

}
