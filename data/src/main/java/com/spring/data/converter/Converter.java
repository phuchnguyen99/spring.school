package com.spring.data.converter;

import com.spring.data.dto.CourseDto;
import com.spring.data.dto.StudentDto;
import com.spring.data.dto.TeacherDto;
import com.spring.data.entity.Course;
import com.spring.data.entity.Student;
import com.spring.data.entity.Teacher;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        final List<StudentDto> studentDtos = new ArrayList<>();
       // course.getStudents().stream().map(s -> studentDtos.add(convertStudentToStudentDto(s)));
        for(Student s : course.getStudents())
        {
            studentDtos.add(convertStudentToStudentDto(s));
        }
        return CourseDto.builder()
                .courseId(course.getCourseId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .credit(course.getCredit())
                .teacherDto(teacherDto)
                .studentList(studentDtos)
                .build();
    }

    public T convertEntityDto(S source, T target)
    {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
