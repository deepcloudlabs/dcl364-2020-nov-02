package com.example.stockmarket.repository.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class JpaStockRepository implements StockRepository {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;

	@Override
	public Optional<Stock> findOne(String symbol) {
		return Optional.ofNullable(entityManager.find(Stock.class, symbol));
	}

	@Override
	public List<Stock> findAll(int page, int size) {
		return entityManager.createNamedQuery("Stock.findAll", Stock.class).setFirstResult(page * size)
				.setMaxResults(size).getResultList();
	}

	@Override
	@Transactional(value = TxType.REQUIRED) // propagation (Spring Tx) -> Transaction Boundary
	public Stock create(Stock stock) {
		entityManager.persist(stock);
		return stock;
	}

	// 1. Persistence Context (Heap) -> Managed Entity
	//    Dirty -> Transactional Method -> Bulk Update
	// 2. MANDATORY -> @Transactional
	@Override
	@Transactional(value=TxType.REQUIRES_NEW)
	public Stock update(Stock entity) {
		var symbol = entity.getSymbol();
		var managedStock = entityManager.find(Stock.class, symbol);
		if (Objects.isNull(managedStock))
			throw new IllegalArgumentException(String.format("Cannot find stock (%s) to update", symbol));
		managedStock.setPrice(entity.getPrice());
		managedStock.setDescription(entity.getDescription());
//		System.err.println("Before merge()...");
//		entityManager.merge(managedStock);
//		entityManager.flush();
//		try {
//			TimeUnit.SECONDS.sleep(12);
//		} catch (InterruptedException e) {
//		}
//		System.err.println("After merge()...");
		return managedStock;
	}

	@Override
	@Transactional
	public Stock removeById(String symbol) {
		var managedStock = entityManager.find(Stock.class, symbol);
		if (Objects.isNull(managedStock))
			throw new IllegalArgumentException(String.format("Cannot find stock (%s) to remove", symbol));
		entityManager.remove(managedStock);
		return managedStock;
	}

	@Override
	@Transactional
	public Stock remove(Stock stock) {
		return this.removeById(stock.getSymbol());
	}

	@Override
	public List<Stock> findByCompany(String company) {
		return entityManager.createNamedQuery("Stock.findByCompany", Stock.class).setParameter("company", company)
				.getResultList();
	}

}
