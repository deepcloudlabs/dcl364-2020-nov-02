package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.entity.Employee;
import com.example.hr.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employees")
@CrossOrigin
public class EmployeeReactiveRestController {
	@Autowired
	private EmployeeService empSrv;

	@GetMapping("{identity}")
	public Mono<Employee> getEmpByIdentity(@PathVariable String identity) {
		return empSrv.findEmployeeById(identity);
	}

	@GetMapping
	public Flux<Employee> getEmps(@RequestParam int page, @RequestParam int size) {
		return empSrv.findAllEmployees(page, size);
	}

	@PostMapping
	public Mono<Employee> getEmps(@RequestBody Employee employee) {
		return empSrv.createEmplyee(employee);
	}
}
