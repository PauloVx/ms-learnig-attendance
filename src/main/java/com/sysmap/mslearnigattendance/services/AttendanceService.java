package com.sysmap.mslearnigattendance.services;

import com.sysmap.mslearnigattendance.entities.Attendance;
import com.sysmap.mslearnigattendance.repositories.AttendanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AttendanceService {

    private AttendanceRepository attendanceRepository;
    private StudentService studentService;

    public AttendanceService(
        AttendanceRepository attendanceRepository,
        StudentService studentService
    ) {
        this.attendanceRepository = attendanceRepository;
    }

    public void save(Attendance attendance) {
        if(!this.studentService.existsById(attendance.getStudentId())) {
            log.warn("Attempted to save a new attendance for invalid student id.");
            return;
        }

        this.attendanceRepository.save(attendance);
        log.info("Saved new Attendance for student with id: " + attendance.getStudentId());
    }
}
