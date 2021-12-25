package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.generated.People;
import com.sconde.xmltodatabase.services.generic.GenericXmlParsingService;
import com.sconde.xmltodatabase.services.interfaces.XmlMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.sconde.xmltodatabase.debug.DebugMessage.*;

@Service
@Log4j2
public class PeopleMessageService implements XmlMessageService {

    @Autowired
    PeopleParsingService parsingService;

    @Override
    public void process(String message) {
        start("process");

        People people = parsingService.parsePeople(message);
        log.debug("Number of persons: " + people.getPerson().size());

        end("process");
    }
}
