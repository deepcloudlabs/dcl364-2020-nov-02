package com.example.stockmarket.batch;

import java.util.Objects;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

public class StockItemReader implements ItemReader<Stock> {
	private StockRepository stockRepository;
	private ItemReader<Stock> delegate;

	public StockItemReader(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public Stock read() throws Exception {
		if (Objects.isNull(delegate))
			delegate = new IteratorItemReader<>(stockRepository.findAll());
		return delegate.read();
	}

}
