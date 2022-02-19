package com.sysmap.mslearnigattendance.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RedisHash("Attendance")
@TypeAlias("Attendance")
public class Attendance
{
    @Id
    private UUID attendanceId;

    @Indexed()
    private UUID studentId;

    private UUID courseId;
    private LocalDateTime classDate;
    private Boolean attendanceStatus;
}
