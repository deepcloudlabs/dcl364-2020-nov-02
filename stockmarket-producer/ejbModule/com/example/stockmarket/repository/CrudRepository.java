package com.example.stockmarket.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E,K> {
	Optional<E> findOne(K key);
	List<E> findAll(int page, int size);
	E create(E entity);
	E update(E entity);
	E removeById(K key);
	E remove(E entity);
}
