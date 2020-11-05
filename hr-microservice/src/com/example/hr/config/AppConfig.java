package com.example.hr.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.SimpleHrApplication;
import com.example.hr.application.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Named
@ApplicationScoped
public class AppConfig {
	@Inject
	private EmployeeRepository employeeRepository;
	@Inject
	private EventPublisher eventPublisher;

	@Produces
	@Named
	@Singleton
	public HrApplication hrApplication() {
		return new SimpleHrApplication(employeeRepository, eventPublisher);
	}
}
