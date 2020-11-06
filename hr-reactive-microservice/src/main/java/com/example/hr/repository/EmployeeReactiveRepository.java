package com.example.hr.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.hr.entity.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeReactiveRepository extends
   ReactiveMongoRepository<Employee, String>{
    @Query("{}")
	Flux<Employee> findAllFlux(Pageable pageable);

}
