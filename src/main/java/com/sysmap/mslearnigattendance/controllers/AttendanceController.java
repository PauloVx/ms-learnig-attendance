package com.sysmap.mslearnigattendance.controllers;

import com.sysmap.mslearnigattendance.controllers.models.RegisterAttendanceInput;
import com.sysmap.mslearnigattendance.entities.Attendance;
import com.sysmap.mslearnigattendance.services.AttendanceService;
import com.sysmap.mslearnigattendance.services.CourseAPIService;
import com.sysmap.mslearnigattendance.services.models.GetAttendancesByStudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/v1")
public class AttendanceController {

    private AttendanceService attendanceService;
    private CourseAPIService courseAPIService;

    public AttendanceController(
        AttendanceService attendanceService,
        CourseAPIService courseAPIService
    ) {
        this.attendanceService = attendanceService;
        this.courseAPIService = courseAPIService;
    }

    @PostMapping("/course/{courseId}/student/{studentId}/attendance")
    public ResponseEntity<Void> registerNewAttendance(
        @PathVariable UUID courseId,
        @PathVariable UUID studentId,
        @RequestBody RegisterAttendanceInput requestBody
    ) {
        var attendance = new Attendance();
        attendance.setAttendanceId(UUID.randomUUID());
        attendance.setStudentId(studentId);
        attendance.setCourseId(courseId);
        attendance.setClassDate(LocalDateTime.now());
        attendance.setAttendanceStatus(requestBody.getAttendanceStatus());

        var savedWithSuccess = this.attendanceService.save(attendance);
        var isCourseIdValid = this.courseAPIService.isCourseIdValid(attendance.getCourseId());

        if(!savedWithSuccess || !isCourseIdValid) return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/attendances")
    public ResponseEntity<GetAttendancesByStudentResponse> getStudentAttendances(
        @PathVariable UUID studentId
    ) {
        var attendances = this.attendanceService.getAttendancesByStudentId(studentId);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }
}
