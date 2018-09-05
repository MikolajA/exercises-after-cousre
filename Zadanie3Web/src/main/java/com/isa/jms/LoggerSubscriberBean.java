package com.isa.jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = "java:/jms/topic/ISA.TOPIC"
        ),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Topic"
        )
})
public class LoggerSubscriberBean implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerSubscriberBean.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                LOG.info("Received message: {}", ((TextMessage) message).getText());
            }
        } catch (Exception e) {
            LOG.error("Error while receiving message", e);
        }
    }
}