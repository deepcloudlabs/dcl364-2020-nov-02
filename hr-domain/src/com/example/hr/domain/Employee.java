package com.example.hr.domain;

// Domain -> Sub-domain -> Bounded Context -> Ubiquitous Language
// Ubiquitous Language: Entity
// Domain Driven Design: Ubiquitous Language -> Bounded Context
// Entity -> Identity, Mutable
// Root Entity -> Aggregate
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

	public Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.birthYear = builder.birthYear;
		this.salary = builder.salary;
		this.account = builder.account;
		this.employeeType = builder.employeeType;
		this.department = builder.department;
		this.photo = builder.photo;
	}

	public static class Builder {
		private final TcKimlikNo identity;
		private Fullname fullname;
		private Money salary;
		private Account account;
		private final BirthYear birthYear;
		private EmployeeType employeeType;
		private Department department;
		private Photo photo;
		
		public Builder(TcKimlikNo identity, BirthYear birthYear) {
			this.identity = identity;
			this.birthYear = birthYear;
		}
		public Builder fullname(String first, String last) {
			this.fullname = new Fullname(first, last);
			return this;
		}
		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}
		public Builder account(Iban iban) {
			this.account = new Account(iban);
			return this;
		}
		public Builder employeeType(EmployeeType employeeType) {
			this.employeeType = employeeType;
			return this;
		}
		public Builder department(Department department) {
			this.department = department;
			return this;
		}
		public Builder photo(byte[] values) {
			this.photo = Photo.valueOf(values);
			return this;
		}
		public Employee build() {
			return new Employee(this);
		}
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
