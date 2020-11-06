package com.example.stockmarket.repository;

import java.util.List;

import com.example.stockmarket.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, String> {
      List<Stock> findByCompany(String company);
}
