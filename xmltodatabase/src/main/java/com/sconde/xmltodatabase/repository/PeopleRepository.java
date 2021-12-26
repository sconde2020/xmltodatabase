package com.sconde.xmltodatabase.repository;

import com.sconde.xmltodatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Student, Long> {
}
