package com.example.hr.adapter;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.hr.application.event.EmployeeEvent;
import com.example.hr.application.infrastructure.EventPublisher;
import com.example.hr.domain.Employee;
import com.example.hr.domain.Fullname;

// Dependency Injection Framework:
// Spring, Java EE -> JBoss SEAM, CDI (Java EE 6), Delta Spike (ASF)
// Guice (Java SE) -> CDI
// CDI: 1. @Inject 
//      2. JPA: @PersistenceContext EntityManager/EntityMangerFactory
//      3. Resource Injection: @Resource -> Managed Resource
//         resources: DataSource / EmailServer / Queue / Topic
//                      ManagedExecutorService (Thread pool) since java ee 7+
// Java EE: How to convert object to JSON
//  JSONP (Java EE 7+) -> Programmatic
//  JSONB (Java EE 8+) -> Declarative 
@Stateless
public class EventPublisherJmsAdapter implements EventPublisher {

	@Inject
	private JMSContext context;
	@Resource(mappedName = "java:/jms/queue/hrQueue")
	private Queue hrQueue;

	@Override
	public void publishEvent(EmployeeEvent employeeHiredEvent) {
		context.createProducer().send(hrQueue, createJson(employeeHiredEvent));
	}

	private String createJson(EmployeeEvent event) {
		Employee employee = event.getEmployee();
		Fullname fullname = employee.getFullname();
		return Json.createObjectBuilder()
				   .add("target", event.getTarget())
				   .add("identity", employee.getIdentity().getValue())
				   .add("firstname", fullname.getFirst())
				   .add("lastname", fullname.getLast())
				   .build()
				   .toString();
	}

}
