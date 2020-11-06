package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.EmployeeType;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.Fullname;
import com.example.hr.domain.Iban;
import com.example.hr.domain.TcKimlikNo;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
	@Id
	@Column(name = "tc_kimlik_no")
	private String identity;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private double salary;
	private String iban;
	@Column(name = "birth_year")
	private int birthYear;
	@Lob
	@Column(name = "resim", columnDefinition = "longblob")
	private byte[] image;
	@Enumerated(EnumType.STRING)
	private Department department;
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;

	public EmployeeEntity() {
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", iban=" + iban + ", birthYear=" + birthYear
				+ ", department=" + department + ", employeeType=" + employeeType + "]";
	}
    // ACL: Anti-Corruption Layer (DDD)
	public void fromEmployee(Employee employee) {
		this.identity = employee.getIdentity().getValue();
		Fullname fullname = employee.getFullname();
		this.firstName = fullname.getFirst();
		this.lastName = fullname.getLast();
		this.salary = employee.getSalary().getValue();
		this.iban = employee.getAccount().getIban().getValue();
		this.image = employee.getPhoto().getValues();
		this.department = employee.getDepartment();
		this.birthYear = employee.getBirthYear().getValue();
	}

	public Employee toEmployee() {
		return new Employee.Builder(TcKimlikNo.valueOf(this.identity), BirthYear.valueOf(this.birthYear))
		        .fullname(this.firstName, this.lastName)
		        .account(Iban.valueOf(this.iban))
		        .salary(salary, FiatCurrency.TRY)
		        .employeeType(EmployeeType.FULLTIME)
		        .photo(this.image)
		        .department(this.department)
				.build();
	}

}