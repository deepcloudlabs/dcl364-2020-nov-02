package com.example.hr.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.FireEmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;
import com.example.hr.dto.HireEmployeeResponse;
/**
 * Hire Employee:
 * curl -X POST http://localhost:8080/hr-microservice/api/v1/employees -d "{\"identity\": \"11111111110\", \"firstName\": \"jack\", \"lastName\": \"bauer\", \"salary\": 100000, \"iban\": \"TR210006267656611494742597\", \"birthYear\": 1956, \"image\": \"\", \"department\": \"SALES\", \"employeeType\": \"FULLTIME\"}" -H "Content-Type: application/json" -H "Accept: application/json"
 * Fire Employee
 * curl -X DELETE http://localhost:8080/hr-microservice/api/v1/employees/11111111110 -H "Accept: application/json"
 */
@Path("/employees")
public class HrController {

	@Inject private HrApplication hrApplication;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request){
		Employee employee = request.toEmployee();
		hrApplication.hireEmployee(employee );
		return new HireEmployeeResponse("success");
	}
	
	@DELETE
	@Path("{identity}")
	@Produces("application/json")
	public FireEmployeeResponse fireEmployee(
			@PathParam("identity") String identity){
		var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity));
		if (employee.isEmpty())
			return new FireEmployeeResponse("error");
		return FireEmployeeResponse.from(employee.get());
	}
	
	
}
