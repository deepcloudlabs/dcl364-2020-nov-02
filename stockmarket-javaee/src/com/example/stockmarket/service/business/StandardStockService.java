package com.example.stockmarket.service.business;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.event.StockPriceChangedEvent;
import com.example.stockmarket.repository.StockRepository;
import com.example.stockmarket.service.StockService;

@Stateless
public class StandardStockService implements StockService {
	@Inject
	private StockRepository stockRepo;
	@PersistenceContext
	private EntityManager em;
	@Inject
	private Event<StockPriceChangedEvent> event;

	@Override
	public Stock findStock(String symbol) {
		System.err.println("StandardStockService::findStock -> " + Thread.currentThread().getName());
		return stockRepo.findOne(symbol).orElseThrow(() -> new IllegalArgumentException("Cannot find stock"));
	}

	@Override
	public List<Stock> findAll(int page, int size) {
		System.err.println(Thread.currentThread().getName() + " is running StandardStockService::findAll");
		return stockRepo.findAll(page, size);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	@Asynchronous
	public Future<Stock> add(Stock stock) {
		return stockRepo.create(stock);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public Stock update(Stock stock) {
		var currentStock = em.find(Stock.class, stock.getSymbol());
		String symbol = stock.getSymbol();
		double oldPrice = currentStock.getPrice();
		double newPrice = stock.getPrice();
		var stockPriceChangedEvent = new StockPriceChangedEvent(symbol, oldPrice, newPrice);
		event.fireAsync(stockPriceChangedEvent);
		return stockRepo.update(stock);
	}

	@Override
	@Transactional
	public Stock delete(String symbol) {
		return stockRepo.removeById(symbol);
	}

}
