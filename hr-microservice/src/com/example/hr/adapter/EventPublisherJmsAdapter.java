package com.example.hr.adapter;

import javax.ejb.Stateless;

import com.example.hr.application.event.EmployeeEvent;
import com.example.hr.application.infrastructure.EventPublisher;

@Stateless
public class EventPublisherJmsAdapter implements EventPublisher {

	@Override
	public void publishEvent(EmployeeEvent employeeHiredEvent) {
		
	}

}
