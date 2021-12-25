package com.sconde.xmltodatabase.listener;

import static com.sconde.xmltodatabase.debug.DebugMessage.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;


@Service
@EnableJms
@Log4j2
public class MessageListener {

    @Value("${message.queue.name}")
    private String queueName;

    @JmsListener(destination = "${message.queue.name}")
    public void onMessage(Message message) throws JMSException {
        start("onMessage");
        log.debug("Received message from " + queueName
                + "\n" + message.getBody(String.class));
        end("onMessage");
    }
}