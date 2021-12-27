package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.exceptions.ParsingException;
import com.sconde.xmltodatabase.generated.people.People;
import com.sconde.xmltodatabase.services.generic.GenericXmlParsingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.sconde.xmltodatabase.utils.DebugMessage.end;
import static com.sconde.xmltodatabase.utils.DebugMessage.start;

@Service
@Log4j2
public class PeopleParsingService extends GenericXmlParsingService {

    @Value("${xjc.generated.people}")
    private String jaxbSourcesPath;

    public People parsePeople(String message) throws ParsingException {
        try {
            start("parsePeople");
            People people = (People) parse(message, jaxbSourcesPath);
            log.debug("People parsed successfully");
            end("parsePeople");
            return people;
        } catch (Exception e) {
            log.error(e.toString());
            throw new ParsingException("Error when parsing people");
        }
    }
}
