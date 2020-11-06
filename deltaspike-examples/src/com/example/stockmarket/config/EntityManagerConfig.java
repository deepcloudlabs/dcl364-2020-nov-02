package com.example.stockmarket.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class EntityManagerConfig {
	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@ApplicationScoped
	@Produces
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
