package com.example.hr.card;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", 
				propertyValue = "javax.jms.Queue")
		,@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "java:/jms/queue/hrQueue")
		})
public class IdentityCardMDB implements MessageListener {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
        	try {
        		var json = (TextMessage) message;
				System.err.println(json.getText());
			} catch (JMSException e) {
				System.err.println("Error while receiving message: "+e.getMessage());
			}
        }
    }

}
