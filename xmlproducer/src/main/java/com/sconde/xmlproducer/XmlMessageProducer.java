package com.sconde.xmlproducer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@EnableJms
@Log4j2
public class XmlMessageProducer {

    @Value("${message.queue.name}")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() throws JmsException, InterruptedException, IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("data/people.xml");
        String inputString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        log.info("Input data:\n" + inputString);

        while (true) {
            Thread.sleep(20000);
            jmsTemplate.convertAndSend(queueName, inputString);
            log.info("Message sent...");
        }
    }
}
