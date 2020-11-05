package com.example.hr.dto;

import java.util.Optional;

import com.example.hr.domain.Employee;

public class FireEmployeeResponse {

	private String status;

	public FireEmployeeResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	private void fromEmployee(Employee employee) {
		
	}

	public static FireEmployeeResponse from(Employee employee) {
		FireEmployeeResponse fireEmployeeResponse = new FireEmployeeResponse("success");
		fireEmployeeResponse.fromEmployee(employee);
		return fireEmployeeResponse;
	}


}
