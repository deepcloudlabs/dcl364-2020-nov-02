package com.example.hr.application.infrastructure;

import com.example.hr.application.event.EmployeeEvent;

public interface EventPublisher {

	void publishEvent(EmployeeEvent employeeHiredEvent);

}
