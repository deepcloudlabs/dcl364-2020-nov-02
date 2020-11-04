package com.example.hr.application.event;

import com.example.hr.domain.Employee;

public class EmployeeHiredEvent extends EmployeeEvent {

	public EmployeeHiredEvent(String target, Employee employee) {
		super(target, employee);
	}

}
