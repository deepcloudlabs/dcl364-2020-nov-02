package com.example.lottery.repository;

import java.util.List;

import org.apache.deltaspike.data.api.FirstResult;
import org.apache.deltaspike.data.api.MaxResults;
import org.apache.deltaspike.data.api.Repository;

import com.example.stockmarket.entity.Stock;

@Repository(forEntity = Stock.class)
public interface StockRepository {

	List<Stock> findAll();

	List<Stock> findAll(@FirstResult int start, @MaxResults int size);

	Stock findBySymbol(String symbol);

	List<Stock> findTop10OrderByPriceDesc();

	void remove(Stock stock);

}
