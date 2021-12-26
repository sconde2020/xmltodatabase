package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.repository.PeopleRepository;
import com.sconde.xmltodatabase.model.Student;
import com.sconde.xmltodatabase.services.interfaces.RecordingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sconde.xmltodatabase.debug.DebugMessage.end;
import static com.sconde.xmltodatabase.debug.DebugMessage.start;

@Service
@Log4j2
public class PeopleRecordingService implements RecordingService {

    @Autowired
    PeopleRepository repository;

    public void record(List<Student> studentList) {
        start("record");

        List<Student> newRecords = repository.saveAll(studentList);
        log.debug("Students recorded successfully");

        end("record");
    }
}
