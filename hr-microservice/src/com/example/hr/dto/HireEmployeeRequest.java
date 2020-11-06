package com.example.hr.dto;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.EmployeeType;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.Iban;
import com.example.hr.domain.TcKimlikNo;

public class HireEmployeeRequest {
	private String identity;
	private String firstName;
	private String lastName;
	private double salary;
	private String iban;
	private int birthYear;
	private String image;
	private Department department;
	private EmployeeType employeeType;

	public HireEmployeeRequest() {
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Employee toEmployee() {
		return new Employee.Builder(TcKimlikNo.valueOf(this.identity), BirthYear.valueOf(this.birthYear))
		        .fullname(this.firstName, this.lastName)
		        .account(Iban.valueOf(this.iban))
		        .salary(salary, FiatCurrency.TRY)
		        .employeeType(EmployeeType.FULLTIME)
		        .photo(this.image.getBytes())
		        .department(this.department)
				.build();	}

}
