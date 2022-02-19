package com.sysmap.mslearnigattendance.services.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AttendanceDTO {
    private UUID courseId;
    private LocalDateTime classDate;
    private Boolean attendanceStatus;
}
