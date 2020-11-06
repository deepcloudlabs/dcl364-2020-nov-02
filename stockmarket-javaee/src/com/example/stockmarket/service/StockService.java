package com.example.stockmarket.service;

import java.util.List;
import java.util.concurrent.Future;

import com.example.stockmarket.entity.Stock;

public interface StockService {

	Stock findStock(String symbol);

	List<Stock> findAll(int page, int size);

	Future<Stock> add(Stock stock);

	Stock update(Stock stock);

	Stock delete(String symbol);

}
