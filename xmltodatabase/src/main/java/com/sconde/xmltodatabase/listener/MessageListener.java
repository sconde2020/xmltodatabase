package com.sconde.xmltodatabase.listener;

import com.sconde.xmltodatabase.controller.MessageController;
import com.sconde.xmltodatabase.exceptions.MappingException;
import com.sconde.xmltodatabase.exceptions.ParsingException;
import com.sconde.xmltodatabase.exceptions.RecordingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;

import java.util.Objects;

import static com.sconde.xmltodatabase.utils.DebugMessage.*;

@Service
@Log4j2
public class MessageListener {

    @Autowired
    private MessageController controller;

    @Value("${message.queue.name}")
    private String queueName;

    @JmsListener(id = "xmlMessageListener",
            containerFactory = "jmsListenerContainerFactory",
            destination = "${message.queue.name}")
    @Transactional( rollbackFor = Exception.class)
    public void onMessage(Message message) throws Exception {
        String messageBody = null;
        try {
            log.debug("");
            start("onMessage");

            messageBody = message.getBody(String.class);
            log.debug("Received message from " + queueName);
            controller.control(messageBody);

            end("onMessage");
        } catch (ParsingException | MappingException e) {
            log.error(e.toString());
            log.error("Verify xml document :\n" + messageBody);
        } catch (Exception e) {
            throw e;
        }
    }
}