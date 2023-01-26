package com.spring.data.converter;

import com.spring.data.dto.CourseDto;
import com.spring.data.dto.StudentDto;
import com.spring.data.entity.Course;
import com.spring.data.entity.Student;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
        return Course.builder()
                .courseId(courseDto.getCourseId())
                .courseCode(courseDto.getCourseCode())
                .courseName(courseDto.getCourseName())
                .credit(courseDto.getCredit())
                .build();
    }

    public CourseDto convertCourseToCourseDto(final Course course)
    {
        return CourseDto.builder()
                .courseId(course.getCourseId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .credit(course.getCredit())
                .build();
    }

    public T convertEntityDto(S source, T target)
    {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
