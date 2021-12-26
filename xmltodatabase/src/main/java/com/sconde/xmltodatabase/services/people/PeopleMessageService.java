package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.generated.People;
import com.sconde.xmltodatabase.model.Student;
import com.sconde.xmltodatabase.services.interfaces.XmlMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sconde.xmltodatabase.debug.DebugMessage.*;

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
    public void process(String message) {
        start("process");

        People people = parsingService.parsePeople(message);
        log.debug("Number of people: " + people.getPerson().size());

        List<Student> studentList = mappingService.mapToStudent(people);
        log.debug("Number of students: " + studentList.size());

        recordingService.record(studentList);
        log.debug("Students recorded");

        end("process");
    }
}
