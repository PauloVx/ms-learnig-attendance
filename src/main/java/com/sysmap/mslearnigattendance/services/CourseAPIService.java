package com.sysmap.mslearnigattendance.services;

import com.sysmap.mslearnigattendance.services.models.GetCourseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class CourseAPIService {
    private final WebClient courseApi;

    private static final String COURSES_URI = "/courses";

    public CourseAPIService(
            WebClient.Builder webClientBuilder,
            @Value("${course-api-url}")
            String courseApiURL
    ) {
        this.courseApi = webClientBuilder.baseUrl(courseApiURL).build();
    }

    public Boolean isCourseIdValid(UUID courseId) {

        GetCourseResponse[] courseApiResponse = getGetCoursesFromAPI(courseId);

        assert courseApiResponse != null;

        return courseApiResponse.length == 1;
    }

    public String getCourseNameById(UUID courseId) {
        GetCourseResponse courseApiResponse = getGetCoursesFromAPI(courseId)[0];

        return courseApiResponse.getCourseName();
    }

    private GetCourseResponse[] getGetCoursesFromAPI(UUID courseId) {
        var courseApiResponse = this.courseApi
                .get()
                .uri(COURSES_URI + "/" + courseId)
                .retrieve()
                .bodyToMono(GetCourseResponse[].class)
                .block();
        return courseApiResponse;
    }
}