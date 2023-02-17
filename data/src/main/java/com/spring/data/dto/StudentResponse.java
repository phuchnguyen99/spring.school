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
public class StudentResponse extends UserDto{

    private Long studentId;

    private String firstName;
    private String lastName;
    private String email;
    List<CourseResponse> courseResponseList = new ArrayList<>();

    public void addCourseToCourseList(final List<Course> courses)
    {
        for(Course course : courses)
        {
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setCourseId(course.getCourseId());
            courseResponse.setCourseName(course.getCourseName());
            courseResponse.setCourseCode(course.getCourseCode());
            courseResponse.setCredit(course.getCredit());
            courseResponseList.add(courseResponse);
        }
    }
}
