package com.liusp.roommv.activeMQ;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class UrlCountReceiver {
	private static final String URL = "tcp://localhost:61616";
	private static final String QUEUE_NAME = "urlCountQueue";

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		try {
			factory = new ActiveMQConnectionFactory("admin", "admin", URL);
			connection = factory.createConnection();
			connection.setClientID("aas");
			connection.start();
			session = connection.createSession(true,
 Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(QUEUE_NAME);
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					try {
						System.out.println(message.getStringProperty("body"));
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			consumer.close();
			session.close();
			connection.close();
		}
	}
}
