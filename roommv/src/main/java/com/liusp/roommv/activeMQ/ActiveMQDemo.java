package com.liusp.roommv.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by jackyliu on 2015/3/16.
 */
public class ActiveMQDemo {
    private static final String URL_BROKER1 = "failover:(tcp://localhost:61616,tcp://localhost:61617)";
    private static final String URL_BROKER2 = "failover:(tcp://localhost:61618,tcp://localhost:61619)";
    private static final String QUEUE_NAME = "urlCountQueue";
    private ActiveMQConnectionFactory factory;
    private Connection connection;
    private UrlCountReceiver receiver;
    private UrlCountSender sender;

    public ActiveMQDemo(UrlCountReceiver receiver, UrlCountSender sender) throws JMSException {
        this.sender = sender;
        this.receiver = receiver;
        this.factory = new ActiveMQConnectionFactory("admin", "admin", URL_BROKER1);
        this.connection = factory.createConnection();
        connection.start();
    }

    public void receive() throws JMSException {
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        receiver.Receive(session, QUEUE_NAME);
    }

    public void send() throws JMSException {
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        sender.send(session, QUEUE_NAME);
    }

    public static void main(String[] args) throws JMSException {
        ActiveMQDemo activeMQDemo = new ActiveMQDemo(new UrlCountReceiver(), new UrlCountSender());
        new Thread(new InnerReceiver(activeMQDemo)).start();
        new Thread(new InnerSender(activeMQDemo)).start();

    }

    static class InnerReceiver implements Runnable {
        private ActiveMQDemo activeMQDemo;

        public InnerReceiver(ActiveMQDemo activeMQDemo) {
            this.activeMQDemo = activeMQDemo;
        }

        @Override
        public void run() {
            try {
                activeMQDemo.receive();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    static class InnerSender implements Runnable {
        private ActiveMQDemo activeMQDemo;

        public InnerSender(ActiveMQDemo activeMQDemo) {
            this.activeMQDemo = activeMQDemo;
        }

        @Override
        public void run() {
            try {
                activeMQDemo.send();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
