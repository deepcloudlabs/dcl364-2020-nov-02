package com.example.stockmarket.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockmarket.document.Stock;
import com.example.stockmarket.repository.StockRepository;
import com.example.stockmarket.service.ReactiveStockService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StandardReactiveStockService implements ReactiveStockService {
	@Autowired
	private StockRepository stockRepo;
	
	@Override
	public Mono<Stock> findStock(String symbol) {
		return stockRepo.findById(symbol);
	}

	@Override
	public Flux<Stock> findAll(int page, int size) {
		return stockRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	@Transactional
	public Mono<Stock> add(Stock stock) {
		return stockRepo.save(stock);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Mono<Stock> update(Stock stock) {
		return stockRepo.save(stock);
	}

	@Override
	@Transactional
	public void delete(String symbol) {
		stockRepo.deleteById(symbol);
	}

}
