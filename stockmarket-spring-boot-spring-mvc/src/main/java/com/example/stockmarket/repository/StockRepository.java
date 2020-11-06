package com.example.stockmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stockmarket.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
      List<Stock> findByCompany(String company);
}
