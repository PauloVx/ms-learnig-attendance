package com.sysmap.mslearnigattendance.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RedisHash("Attendance")
public class Attendance
{
    @Id
    private UUID attendanceId;

    private UUID studentId;
    private UUID courseId;
    private LocalDateTime classDate;
    private Boolean attendanceStatus;
}
