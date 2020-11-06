package com.example.lottery.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.example.stockmarket.entity.Stock;

@Repository
public interface FullStockRepository extends FullEntityRepository<Stock, String> {

}
