package com.sysmap.mslearnigattendance.services;

import com.sysmap.mslearnigattendance.entities.Student;
import com.sysmap.mslearnigattendance.services.models.CreatedStudentEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaEventService {
    @Value(value = "${spring.kafka.create-student.topic.name}")
    private String createdStudentTopic = "create-student-topic";

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    private final StudentService studentService;

    public KafkaEventService(
        StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @KafkaListener(
        topics = "${spring.kafka.create-student.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "studentKafkaListenerContainerFactory"
    )
    public void createdStudentEventListener(ConsumerRecord<String, CreatedStudentEventDTO> record) {
        log.info("Received message, Partition: " + record.partition() + " Topic: " + record.topic());

        Student student = new Student();
        BeanUtils.copyProperties(record.value(), student);

        this.studentService.save(student);
    }
}
