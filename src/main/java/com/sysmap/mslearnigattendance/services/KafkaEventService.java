package com.sysmap.mslearnigattendance.services;

import com.sysmap.mslearnigattendance.services.models.CreatedStudentEventDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventService {
    @Value(value = "${spring.kafka.create-student.topic.name}")
    private String createdStudentTopic = "create-student-topic";

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    @KafkaListener(
        topics = "${spring.kafka.create-student.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "studentKafkaListenerContainerFactory"
    )
    public void createdStudentEventListener(CreatedStudentEventDTO eventData) {
        System.out.println("Consumed Event, Data: " + eventData.toString());
    }
}
