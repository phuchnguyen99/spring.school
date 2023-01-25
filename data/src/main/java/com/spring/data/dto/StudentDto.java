package com.spring.data.dto;


import com.spring.data.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    List<CourseDto> courseDtoList = new ArrayList<>();

    public void addCourseToCourseList(final List<Course> courses)
    {
        for(Course course : courses)
        {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseCode(course.getCourseCode());
            courseDto.setCredit(course.getCredit());
            courseDtoList.add(courseDto);
        }
    }
}
