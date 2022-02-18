package com.sysmap.mslearnigattendance.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Data
@RedisHash("Student")
public class Student {
    @Id
    private UUID studentId;

    private String fullName;
    private UUID courseId;
}
