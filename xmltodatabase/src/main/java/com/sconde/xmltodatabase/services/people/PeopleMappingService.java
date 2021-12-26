package com.sconde.xmltodatabase.services.people;

import com.sconde.xmltodatabase.generated.People;
import com.sconde.xmltodatabase.generated.People.Person;
import com.sconde.xmltodatabase.model.Student;
import com.sconde.xmltodatabase.services.interfaces.MappingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sconde.xmltodatabase.utils.DebugMessage.*;


@Service
@Log4j2
public class PeopleMappingService implements MappingService {

    public List<Student> mapToStudent(People people) {
        start("mapToStudent");

        List<Student> studentList = new ArrayList<>();

        for(Person person : people.getPerson()) {
            Student student = new Student();
            student.setName(person.getName());
            student.setEmailAddress(person.getEmailAddress());
            student.setPurchasedPackage(person.getPurchasedPackage());
            studentList.add(student);
        }

        if (!studentList.isEmpty()) {
            log.debug("People mapped to students successfully");
        }

        end("mapToStudent");
        return studentList;
    }
}
