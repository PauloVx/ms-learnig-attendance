package com.sysmap.mslearnigattendance.services;

import com.sysmap.mslearnigattendance.entities.Attendance;
import com.sysmap.mslearnigattendance.repositories.AttendanceRepository;
import com.sysmap.mslearnigattendance.services.models.AttendanceDTO;
import com.sysmap.mslearnigattendance.services.models.GetAttendancesByStudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AttendanceService {

    private AttendanceRepository attendanceRepository;
    private StudentService studentService;
    private CourseAPIService courseAPIService;

    public AttendanceService(
        AttendanceRepository attendanceRepository,
        StudentService studentService,
        CourseAPIService courseAPIService
    ) {
        this.attendanceRepository = attendanceRepository;
        this.studentService = studentService;
        this.courseAPIService = courseAPIService;
    }

    public Boolean save(Attendance attendance) {
        if(!this.studentService.existsById(attendance.getStudentId())) {
            log.warn("Attempted to save a new attendance for invalid student id.");
            return false;
        }

        this.attendanceRepository.save(attendance);
        log.info("Saved new Attendance for student with id: " + attendance.getStudentId());
        return true;
    }

    public GetAttendancesByStudentResponse getAttendancesByStudentId(UUID studentId) {
        var attendances = this.attendanceRepository.findAttendancesByStudentId(studentId);

        var student = this.studentService.getStudentById(studentId);
        String fullName = student.getFullName();
        String courseName = this.courseAPIService.getCourseNameById(student.getCourseId());

        var attendanceDTOS = new ArrayList<AttendanceDTO>();
        for(var attendance : attendances) {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            BeanUtils.copyProperties(attendance, attendanceDTO);
            attendanceDTOS.add(attendanceDTO);
        }

        var attendanceResponse = new GetAttendancesByStudentResponse();
        attendanceResponse.setFullName(fullName);
        attendanceResponse.setCourseName(courseName);
        attendanceResponse.setAttendances(attendanceDTOS);

        return attendanceResponse;
    }
}
