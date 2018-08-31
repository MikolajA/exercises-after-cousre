package com.infoshareacademy.zadanie3;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer implements Runnable, ExceptionListener {

    public static void main(String[] args) {

        new Consumer().run();
    }

    @Override
    public void run() {

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("ISA.MSG.TOPIC");

            MessageConsumer consumer = session.createConsumer(destination);

            System.out.println("Started...");

            while (true) {

                Message message = consumer.receive();

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println(text);
                    if (text.equals("exit")) {
                        break;
                    }
                }
            }

            consumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            System.out.println("Caugth: " + e);
        }
    }

    @Override
    public void onException(JMSException e) {
        System.out.println("JMS Exception occured: " + e);
    }
}
