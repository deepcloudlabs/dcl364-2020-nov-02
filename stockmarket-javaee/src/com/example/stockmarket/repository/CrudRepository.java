package com.example.stockmarket.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import com.example.stockmarket.entity.Stock;

public interface CrudRepository<E, K> {
	Optional<E> findOne(K key);

	List<E> findAll(int page, int size);

	Future<Stock> create(E entity);

	E update(E entity);

	E removeById(K key);

	E remove(E entity);
}
