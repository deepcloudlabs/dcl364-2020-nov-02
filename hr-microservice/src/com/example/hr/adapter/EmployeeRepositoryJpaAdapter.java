package com.example.hr.adapter;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeRepository;

@Stateless
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
    @PersistenceContext(unitName = "hrPU")
    private EntityManager em;
    
	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		var entity = em.find(EmployeeEntity.class, identity.getValue());
		return Objects.nonNull(entity);
	}

	@Override
	@Transactional
	public Employee create(Employee employee) {
		var entity = new EmployeeEntity();
		entity.fromEmployee(employee);
		em.persist(entity);
		return employee;
	}

	@Override
	@Transactional
	public Employee removeByIdentity(TcKimlikNo identity) {
		var entity = em.find(EmployeeEntity.class, identity.getValue());
		if (Objects.isNull(entity))
			throw new IllegalArgumentException("Cannot find employee to remove.");
	    em.remove(entity);	
		return entity.toEmployee();
	}

}
