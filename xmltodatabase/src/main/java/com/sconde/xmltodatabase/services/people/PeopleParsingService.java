package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.generated.People;
import com.sconde.xmltodatabase.services.generic.GenericXmlParsingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.sconde.xmltodatabase.debug.DebugMessage.end;
import static com.sconde.xmltodatabase.debug.DebugMessage.start;

@Service
public class PeopleParsingService extends GenericXmlParsingService {

    @Value("${xjc.generated.people}")
    private String jaxbSourcesPath;

    public People parsePeople(String message) {
        start("parsePeople");
        People people = null;
        people = (People) parse(message, jaxbSourcesPath);
        end("parsePeople");
        return people;
    }
}
