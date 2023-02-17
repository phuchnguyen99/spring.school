package com.spring.data.converter;

import com.spring.data.dto.*;
import com.spring.data.entity.Course;
import com.spring.data.entity.Student;
import com.spring.data.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Converter<S, T>
{
    public StudentResponse convertStudentToStudentDto(final Student student)
    {
        final StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.addCourseToCourseList(student.getCourseList());
        return studentResponse;
    }

    public Student convertStudentRequestToStudent(final StudentRequest studentRequest)
    {
        return Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .build();
    }

    public Course convertCourseRequestToCourse(final CourseRequest courseRequest)
    {
        final TeacherDto teacherDto = courseRequest.getTeacherDto();
        final Teacher teacher = Teacher.builder()
                .id(teacherDto.getId())
                .department(teacherDto.getDepartment())
                .build();
        return Course.builder()
                .courseCode(courseRequest.getCourseCode())
                .courseName(courseRequest.getCourseName())
                .credit(courseRequest.getCredit())
                .teacher(teacher)
                .build();
    }

    public CourseResponse convertCourseToCourseResponse(final Course course)
    {
        final Teacher teacher = course.getTeacher();
        TeacherDto teacherDto = null;
        if(teacher != null) {
             teacherDto = TeacherDto.builder()
                    .department(teacher.getDepartment())
                    .build();
        }
        final List<StudentResponse> studentResponses = course.getStudents()
                .stream().map(this::convertStudentToStudentDto).collect(Collectors.toList());
        return CourseResponse.builder()
                .courseId(course.getCourseId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .credit(course.getCredit())
                .teacherDto(teacherDto)
                .studentList(studentResponses)
                .build();
    }

}
