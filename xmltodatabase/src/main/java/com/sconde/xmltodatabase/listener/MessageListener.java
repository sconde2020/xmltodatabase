package com.sconde.xmltodatabase.listener;

import com.sconde.xmltodatabase.controller.MessageController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

import static com.sconde.xmltodatabase.debug.DebugMessage.*;

@Service
@EnableJms
@Log4j2
public class MessageListener {

    @Value("${message.queue.name}")
    private String queueName;

    @Autowired
    private MessageController controller;

    @JmsListener(destination = "${message.queue.name}")
    public void onMessage(Message message) throws JMSException {
        log.debug("");
        start("onMessage");

        String content = message.getBody(String.class);
        log.debug("Received message from " + queueName);
        controller.control(content);

        end("onMessage");
    }
}