package com.sysmap.mslearnigattendance.controllers;

import com.sysmap.mslearnigattendance.repositories.AttendanceRepository;
import org.springframework.stereotype.Controller;

@Controller
public class AttendanceController {

    private AttendanceRepository attendanceRepository;

    public AttendanceController(
        AttendanceRepository attendanceRepository
    ) {
        this.attendanceRepository = attendanceRepository;
    }
}
