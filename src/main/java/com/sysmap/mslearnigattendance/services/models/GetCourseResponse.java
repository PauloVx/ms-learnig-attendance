package com.sysmap.mslearnigattendance.services.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetCourseResponse {
    private UUID courseId;
    private String courseName;
    private Boolean status;
    private LocalDateTime createdOn;
}