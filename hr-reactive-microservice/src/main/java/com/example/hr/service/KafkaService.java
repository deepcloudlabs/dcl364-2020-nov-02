package com.example.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.entity.Employee;

@Service
public class KafkaService {
	@Autowired private KafkaTemplate<String, Employee> kt;
	@EventListener
	public void listen(Employee emp) {
		kt.send("employees", emp.getIdentity(), emp);
	}
}
