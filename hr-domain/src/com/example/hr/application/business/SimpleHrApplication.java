package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.event.EmployeeFiredEvent;
import com.example.hr.application.event.EmployeeHiredEvent;
import com.example.hr.application.infrastructure.EventPublisher;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

public class SimpleHrApplication implements HrApplication {

	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;
	
	public SimpleHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public boolean hireEmployee(Employee employee) {
		var identity = employee.getIdentity();
		if(employeeRepository.existsByIdentity(identity))
			return false;
		employeeRepository.create(employee);
		eventPublisher.publishEvent(new EmployeeHiredEvent("employees",employee));
		return true;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		if(!employeeRepository.existsByIdentity(identity))
			return Optional.empty();
		var employee = employeeRepository.removeByIdentity(identity);
		eventPublisher.publishEvent(new EmployeeFiredEvent("employees",employee));
		return Optional.of(employee);
	}

}
