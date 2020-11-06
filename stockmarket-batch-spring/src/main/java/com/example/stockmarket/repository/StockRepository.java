package com.example.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stockmarket.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{

}
