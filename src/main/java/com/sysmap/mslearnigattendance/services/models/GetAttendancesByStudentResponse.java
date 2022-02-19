package com.sysmap.mslearnigattendance.services.models;

import lombok.Data;

import java.util.List;

@Data
public class GetAttendancesByStudentResponse {
    private String fullName;
    private String courseName;
    private List<AttendanceDTO> attendances;
}
