package com.example.hr.domain;

// Domain -> Sub-domain -> Bounded Context -> Ubiquitous Language
// Ubiquitous Language: Entity
// Domain Driven Design: Ubiquitous Language -> Bounded Context
// Entity -> Identity, Mutable
public class Employee {
	private final TcKimlikNo identity;
	private Fullname fullname;
	private Money salary;
	private Account account;
	private final BirthYear birthYear;
	private EmployeeType employeeType;
	private Department department;
	private Photo photo;

	public Employee(TcKimlikNo identity, Fullname fullname, BirthYear birthYear) {
		this.identity = identity;
		this.fullname = fullname;
		this.birthYear = birthYear;
	}

	public Employee(TcKimlikNo identity, Fullname fullname, Money salary, Account account, BirthYear birthYear,
			EmployeeType employeeType, Department department, Photo photo) {
		this.identity = identity;
		this.fullname = fullname;
		this.salary = salary;
		this.account = account;
		this.birthYear = birthYear;
		this.employeeType = employeeType;
		this.department = department;
		this.photo = photo;
	}

	public Fullname getFullname() {
		return fullname;
	}

	public void setFullname(Fullname fullname) {
		this.fullname = fullname;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public BirthYear getBirthYear() {
		return birthYear;
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
		Employee other = (Employee) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

}
