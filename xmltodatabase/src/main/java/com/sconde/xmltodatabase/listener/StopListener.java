package com.sconde.xmltodatabase.listener;

import com.sconde.xmltodatabase.listener.MessageListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Log4j2
public class StopListener {

    @Autowired
    JmsListenerEndpointRegistry jmsListenerEndpointRegistry;

    @PostConstruct
    public void createAppHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            MessageListenerContainer listenerContainer = jmsListenerEndpointRegistry.getListenerContainer("xmlMessageListener");
            if (listenerContainer != null) {
                listenerContainer.stop();
                log.info("Message Listener stopped");
            }
        }));
    }

}
