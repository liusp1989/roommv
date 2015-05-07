package com.liusp.roommv.activeMQ;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.UUID;

public class UrlCountSender {

	public  void send(final Session session,String queueName) throws JMSException {
		try {
			Destination destination = session.createQueue(queueName);
			Destination tempDestination = session.createTemporaryQueue();
			MessageProducer producer  = session.createProducer(destination);
			TextMessage message = session.createTextMessage();
			message.setStringProperty("body", "呵呵呵恶化");
			message.setJMSReplyTo(tempDestination);
			message.setJMSCorrelationID(UUID.randomUUID().toString());
			producer.send(message);
			MessageConsumer consumer = session.createConsumer(tempDestination);
			MapMessage msg = (MapMessage) consumer.receive();
			System.out.println(msg.getString("rep"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
