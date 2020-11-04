package com.example.hr.repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface EmployeeRepository {

	boolean existsByIdentity(TcKimlikNo identity);

	Employee create(Employee employee);

	Employee removeByIdentity(TcKimlikNo identity);

}
