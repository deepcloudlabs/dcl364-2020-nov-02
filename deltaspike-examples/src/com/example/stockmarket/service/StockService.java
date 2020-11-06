package com.example.stockmarket.service;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.lottery.repository.FullStockRepository;
import com.example.lottery.repository.StockRepository;

@Stateless
public class StockService {
	@Inject
	private StockRepository stockRepo;
	@Inject
	private FullStockRepository fullStockRepo;

	@Schedule(second = "*/7", hour = "*", minute = "*")
	public void listStockPrices() {
		stockRepo.findAll().forEach(System.err::println);
		stockRepo.findTop10OrderByPriceDesc().forEach(System.err::println);
		fullStockRepo.findAll(0, 10).forEach(System.err::println);
		var orcl = fullStockRepo.findBy("orcl");
		System.err.println(orcl);
	}
}
