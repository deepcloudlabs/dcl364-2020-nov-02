package com.example.stockmarket.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

// Web Container : Presentation
// Component Model: CDI, Servlet, JSP, JSF, ...
// EJB Lite: @Stateless (Local Bean)
// EJB Container : Business Logic + Integration
// Component Model : EJB Component
// I. Session Bean (Simple/Instant BL)
// 1. Stateless Session Bean: @Stateless
// 2. Stateful Session Bean: @Stateful
// 3. Singleton : @Singleton (Java EE 6)
// II. Message-Driven Bean -> @MessageDriven (Async. JMS Consumer)
// Long running business process
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/stockQueue") })
// standalone-full.xml:
//  <subsystem xmlns="urn:jboss:domain:messaging-activemq:10.0">
//    <server name="default">
//      . . .
//      <jms-queue name="stockQueue" entries="java:/jms/queue/stockQueue"/>
//      . . .
//    </server>
//   </subsystem>
public class StockmarketMDB implements MessageListener {
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			var textMessage = (TextMessage) message;
			try {
				System.err.println("Message received from MDB: " + textMessage.getText());
			} catch (JMSException e) {
				System.err.println("Error in reading text message: " + e.getMessage());
			}
		}

	}

}
