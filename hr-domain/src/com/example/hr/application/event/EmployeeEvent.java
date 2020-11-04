package com.example.hr.application.event;

import com.example.hr.domain.Employee;

public class EmployeeEvent {
	private final String target;
	private final Employee employee;

	public EmployeeEvent(String target, Employee employee) {
		this.target = target;
		this.employee = employee;
	}

	public String getTarget() {
		return target;
	}

	public Employee getEmployee() {
		return employee;
	}

}
