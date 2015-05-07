package com.liusp.roommv.activeMQ;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class UrlCountReceiver {
	public void Receive(final Session session,String queueName) throws JMSException {
		try {
			MessageConsumer consumer = null;
			Destination destination = session.createQueue(queueName);
			consumer = session.createConsumer(destination);
			final MessageProducer reply = session.createProducer(null);
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					try {
						if(message.getJMSReplyTo()!=null){
							MapMessage mapMessage = session.createMapMessage();
							mapMessage.setString("rep","ok");
							reply.send(message.getJMSReplyTo(),mapMessage);
						}
						System.out.println(message.getStringProperty("body"));
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
