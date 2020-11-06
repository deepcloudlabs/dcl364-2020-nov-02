package com.example.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private EmployeeReactiveRepository empRepo;

	public Mono<Employee> findEmployeeById(String identity) {
		return empRepo.findById(identity);
	}

	public Flux<Employee> findAllEmployees(int page, int size) {
		return empRepo.findAllFlux(PageRequest.of(page, size));
	}

	public Mono<Employee> createEmplyee(Employee employee) {
		publisher.publishEvent(employee);
		return empRepo.save(employee);
	}
	
}
