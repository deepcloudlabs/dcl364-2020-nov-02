package com.example.stockmarket.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.stockmarket.entity.Stock;

public interface StockService {

	Stock findStock(String symbol);

	CompletableFuture<List<Stock>> findAll(int page, int size);

	Stock add(Stock stock);

	Stock update(Stock stock);

	Stock delete(String symbol);

}
