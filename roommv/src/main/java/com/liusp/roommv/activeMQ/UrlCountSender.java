package com.liusp.roommv.activeMQ;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class UrlCountSender {
	private static final String URL = "tcp://localhost:61616";
	private static final String QUEUE_NAME = "urlCountQueue";

	public static void main(String[] args) throws JMSException {

		ActiveMQConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			factory = new ActiveMQConnectionFactory("admin", "admin", URL);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(QUEUE_NAME);
			producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();
			message.setStringProperty("body", "呵呵呵恶化");
			producer.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			producer.close();
			session.close();
			connection.close();
		}
	}
}
