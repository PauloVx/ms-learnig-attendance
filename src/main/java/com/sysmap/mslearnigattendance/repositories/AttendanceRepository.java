package com.sysmap.mslearnigattendance.repositories;

import com.sysmap.mslearnigattendance.entities.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, UUID> {
    List<Attendance> findAttendancesByStudentId(UUID studentId);
}
