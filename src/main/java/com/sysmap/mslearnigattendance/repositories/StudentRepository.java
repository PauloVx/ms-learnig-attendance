package com.sysmap.mslearnigattendance.repositories;

import com.sysmap.mslearnigattendance.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {
}
