package com.example.hr.application.event;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends EmployeeEvent {

	public EmployeeFiredEvent(String target, Employee employee) {
		super(target, employee);
	}

}
