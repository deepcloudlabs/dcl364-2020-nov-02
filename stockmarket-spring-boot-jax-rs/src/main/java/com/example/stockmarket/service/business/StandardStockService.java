package com.example.stockmarket.service.business;

import java.util.List;

import javax.inject.Inject;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.event.StockPriceChangedEvent;
import com.example.stockmarket.repository.StockRepository;
import com.example.stockmarket.service.StockService;

@Service
public class StandardStockService implements StockService {
	@Inject
	private StockRepository stockRepo;

	@Inject
    private ApplicationEventPublisher publisher;
	
	@Override
	public Stock findStock(String symbol) {
		return stockRepo.findById(symbol).orElseThrow(() -> new IllegalArgumentException("Cannot find stock"));
	}

	@Override
	public List<Stock> findAll(int page, int size) {
		return stockRepo.findAll(PageRequest.of(page, size)).getContent();
	}

	@Override
	@Transactional
	public Stock add(Stock stock) {
		return stockRepo.save(stock);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Stock update(Stock stock) {
		var currentStock = stockRepo.findById(stock.getSymbol());
		String symbol= stock.getSymbol();
		double oldPrice= currentStock.get().getPrice();
		double newPrice= stock.getPrice();
		var stockPriceChangedEvent = new StockPriceChangedEvent(symbol, oldPrice, newPrice);
		publisher.publishEvent(stockPriceChangedEvent);
		return stockRepo.save(stock);
	}

	@Override
	@Transactional
	public Stock delete(String symbol) {
		var managedStock= stockRepo.findById(symbol);
		if (managedStock.isEmpty())
			throw new IllegalArgumentException("Cannot find stock to delete");
		Stock stock = managedStock.get();
		stockRepo.delete(stock);
		return stock;
	}

}
