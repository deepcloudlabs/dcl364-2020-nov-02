package com.example.hr.adapter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

@Stateless
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
    @PersistenceContext(unitName = "hrPU")
    private EntityManager em;
    
	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee create(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee removeByIdentity(TcKimlikNo identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
