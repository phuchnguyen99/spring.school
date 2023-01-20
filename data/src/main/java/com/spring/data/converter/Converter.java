package com.spring.data.converter;

import com.spring.data.dto.StudentDto;
import com.spring.data.entity.Student;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class Converter
{
    private final StudentRepository studentRepository;

    public Converter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto convertStudentToStudentDto(final Student student)
    {
        final StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    public Student convertStudentDtoToStudent(final StudentDto studentDto)
    {
        final Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        return student;
    }
}
