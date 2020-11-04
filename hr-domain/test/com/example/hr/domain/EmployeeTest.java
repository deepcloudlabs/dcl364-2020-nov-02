package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	@Test
	void createEmployee() {
		var employee = new Employee.Builder(TcKimlikNo.valueOf("11111111110"),BirthYear.valueOf(1956))
		            .fullname("jack", "bauer")
		            .account(Iban.valueOf("TR460006278762491494664675"))
                    .salary(100_000,FiatCurrency.TRY)
                    .employeeType(EmployeeType.FULLTIME)
                    .department(Department.SALES)
                    .photo("".getBytes())
                    .build();
		
		assertEquals("11111111110", employee.getIdentity().getValue());           
         assertEquals("jack", employee.getFullname().getFirst());           
         assertEquals("bauer", employee.getFullname().getLast());           
	}

}
