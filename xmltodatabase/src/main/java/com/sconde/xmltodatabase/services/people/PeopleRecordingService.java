package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.exceptions.RecordingException;
import com.sconde.xmltodatabase.repository.PeopleRepository;
import com.sconde.xmltodatabase.model.Student;
import com.sconde.xmltodatabase.services.interfaces.RecordingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sconde.xmltodatabase.utils.DebugMessage.end;
import static com.sconde.xmltodatabase.utils.DebugMessage.start;

@Service
@Log4j2
public class PeopleRecordingService implements RecordingService {

    @Autowired
    PeopleRepository repository;

    @Transactional
    public void record(List<Student> studentList) throws RecordingException {
        try {
            start("record");

            repository.saveAll(studentList);
            log.debug("Students recorded successfully");

            end("record");

        } catch (Exception e) {
            log.error(e.toString());
            throw new RecordingException("Error when inserting into database");
        }
    }
}
