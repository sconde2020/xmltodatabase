package com.sconde.xmlproducer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@EnableJms
@Log4j2
public class XmlMessageProducer {

    @Value("${message.queue.name}")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() throws JmsException, InterruptedException {
        while (true) {
            Thread.sleep(10000);
            jmsTemplate.convertAndSend(queueName, "<salut>Hello From " + queueName + "</salut>");
            log.info("Message sent...");
        }
    }
}
