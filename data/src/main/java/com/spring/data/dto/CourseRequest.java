package com.spring.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {
    private String courseName;
    private String courseCode;
    private int credit;
    private TeacherDto teacherDto;
}
