package com.sconde.xmltodatabase.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StartListener implements CommandLineRunner {

    @Autowired
    JmsListenerEndpointRegistry jmsListenerEndpointRegistry;

    @Override
    public void run(String... args) throws Exception {
        MessageListenerContainer listener = jmsListenerEndpointRegistry.getListenerContainer("xmlMessageListener");
        if (listener != null) {
            listener.start();
            log.info("Message Listener started");
        }
    }
}
