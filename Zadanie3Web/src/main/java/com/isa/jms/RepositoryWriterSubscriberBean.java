package com.isa.jms;

import com.isa.data.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
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
public class RepositoryWriterSubscriberBean implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(RepositoryWriterSubscriberBean.class);

    @Inject
    private MessageRepository messageRepository;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = null; ((TextMessage) message).getText();
                messageRepository.addMessage(text);
            }
        } catch (Exception e) {
            LOG.error("Error while receiving message", e);
        }
    }
}
