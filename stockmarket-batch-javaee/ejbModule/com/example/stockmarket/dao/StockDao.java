package com.example.stockmarket.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Stateless
public class StockDao {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;

	public List<Stock> findAll() {
		return entityManager.createNamedQuery("Stock.findAll", Stock.class).getResultList();
	}
	
}
