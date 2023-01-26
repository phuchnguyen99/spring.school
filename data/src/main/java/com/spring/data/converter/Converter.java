package com.spring.data.converter;

import com.spring.data.dto.CourseDto;
import com.spring.data.dto.StudentDto;
import com.spring.data.dto.TeacherDto;
import com.spring.data.entity.Course;
import com.spring.data.entity.Student;
import com.spring.data.entity.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Converter<S, T>
{
    public StudentDto convertStudentToStudentDto(final Student student)
    {
        final StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.addCourseToCourseList(student.getCourseList());
        return studentDto;
    }

    public Student convertStudentDtoToStudent(final StudentDto studentDto)
    {
        final Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        return student;
    }

    public Course convertCourseDtoToCourse(final CourseDto courseDto)
    {
        final TeacherDto teacherDto = courseDto.getTeacherDto();
        final Teacher teacher = Teacher.builder()
                .id(teacherDto.getId())
                .department(teacherDto.getDepartment())
                .build();
        return Course.builder()
                .courseId(courseDto.getCourseId())
                .courseCode(courseDto.getCourseCode())
                .courseName(courseDto.getCourseName())
                .credit(courseDto.getCredit())
                .teacher(teacher)
                .build();
    }

    public CourseDto convertCourseToCourseDto(final Course course)
    {
        final Teacher teacher = course.getTeacher();
        TeacherDto teacherDto = null;
        if(teacher != null) {
             teacherDto = TeacherDto.builder()
                    .department(teacher.getDepartment())
                    .build();
        }
        final List<StudentDto> studentDtos = course.getStudents()
                .stream().map(this::convertStudentToStudentDto).collect(Collectors.toList());
        return CourseDto.builder()
                .courseId(course.getCourseId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .credit(course.getCredit())
                .teacherDto(teacherDto)
                .studentList(studentDtos)
                .build();
    }
}
