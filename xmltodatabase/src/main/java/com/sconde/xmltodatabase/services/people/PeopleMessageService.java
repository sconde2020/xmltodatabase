package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.exceptions.MappingException;
import com.sconde.xmltodatabase.exceptions.ParsingException;
import com.sconde.xmltodatabase.exceptions.RecordingException;
import com.sconde.xmltodatabase.generated.people.People;
import com.sconde.xmltodatabase.model.Student;
import com.sconde.xmltodatabase.services.interfaces.XmlMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sconde.xmltodatabase.utils.DebugMessage.*;

@Service
@Log4j2
public class PeopleMessageService implements XmlMessageService {

    @Autowired
    PeopleParsingService parsingService;

    @Autowired
    PeopleMappingService mappingService;

    @Autowired
    PeopleRecordingService recordingService;

    @Override
    public void process(String message) throws ParsingException, MappingException, RecordingException {
        try {
            start("process");

            People people = parsingService.parsePeople(message);
            log.debug("Number of people: " + people.getPerson().size());

            List<Student> studentList = mappingService.mapToStudent(people);
            log.debug("Number of students: " + studentList.size());

            recordingService.record(studentList);
            log.debug("Students recorded");

            end("process");
        } catch (ParsingException | MappingException | RecordingException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.toString());
            throw e;
        }
    }
}
