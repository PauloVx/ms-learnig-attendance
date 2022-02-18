package com.sysmap.mslearnigattendance.services;

import com.sysmap.mslearnigattendance.entities.Student;
import com.sysmap.mslearnigattendance.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(
        StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        this.studentRepository.save(student);
        log.info("Saved new Student - Id: " + student.getStudentId());
    }
}
