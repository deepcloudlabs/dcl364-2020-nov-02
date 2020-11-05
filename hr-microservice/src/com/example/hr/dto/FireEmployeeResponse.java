package com.example.hr.dto;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.Fullname;

public class FireEmployeeResponse {
	private String identity;
	private String firstName;
	private String lastName;
	private int birthYear;
	private Department department;
	private String status;

	public FireEmployeeResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private void fromEmployee(Employee employee) {
		this.identity = employee.getIdentity().getValue();
		Fullname fullname = employee.getFullname();
		this.firstName = fullname.getFirst();
		this.lastName = fullname.getLast();
		this.birthYear = employee.getBirthYear().getValue();
		this.department = employee.getDepartment();
	}

	public static FireEmployeeResponse from(Employee employee) {
		FireEmployeeResponse fireEmployeeResponse = new FireEmployeeResponse("success");
		fireEmployeeResponse.fromEmployee(employee);
		return fireEmployeeResponse;
	}

}
