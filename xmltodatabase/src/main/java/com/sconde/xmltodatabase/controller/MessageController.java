package com.sconde.xmltodatabase.controller;

import com.sconde.xmltodatabase.services.people.PeopleMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sconde.xmltodatabase.debug.DebugMessage.*;

@Service
@Log4j2
public class MessageController {

    @Autowired
    PeopleMessageService peopleMessageService;

    public void control(String message) {
        start("control");

        if (message.contains("<people>") && message.contains("</people>")) {
            log.debug("People message: " + "\n" + message);
            peopleMessageService.process(message);
        }

        end("control");
    }

}
