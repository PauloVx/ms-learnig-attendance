package com.sysmap.mslearnigattendance.services.models;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatedStudentEventDTO {
    private UUID studentId;
    private String fullName;
    private UUID courseId;
}
