package com.sconde.xmltodatabase.controller;

import com.sconde.xmltodatabase.services.common.XmlMessageServiceFactory;
import com.sconde.xmltodatabase.utils.MessageTag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.sconde.xmltodatabase.utils.DebugMessage.*;

@Service
@Log4j2
public class MessageController {

    @Autowired
    private XmlMessageServiceFactory serviceFactory;

    @Autowired
    private MessageTag messageTag;

    private List<String> tagList;

    @PostConstruct
    public void init() throws IllegalAccessException {
        tagList = new ArrayList<>();
        Field[] fields = messageTag.getClass().getDeclaredFields();
        for (Field field : fields) {
            String tag = (String) field.get(messageTag);
            tagList.add(tag);
        }
    }

    public void control(String message) {
        start("control");
        boolean servicePresent = false;

        for (String tag : tagList) {
            if (message.contains(tag)) {
                log.debug(tag + " message");
                serviceFactory.getService(tag).process(message);
                servicePresent = true;
            }
        }

        if(!servicePresent) {
            log.warn("No service for this message");
        }
        end("control");
    }

}
