package com.spring.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long courseId;
    private String courseName;
    private String courseCode;
    private int credit;
    List<StudentDto> studentList;
    private TeacherDto teacherDto;

}
