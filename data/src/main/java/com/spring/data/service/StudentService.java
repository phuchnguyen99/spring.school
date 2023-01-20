package com.spring.data.service;

import com.spring.data.excepttion.UserException;
import com.spring.data.dto.StudentDto;

import java.util.List;

public interface StudentService
{
    StudentDto getStudent(Long studentId) throws UserException ;

    void saveStudent(StudentDto studentDto) throws UserException;

    void deleteStudent(Long studentId) throws UserException;

    void updateStudent(Long studentId, StudentDto studentDto) throws UserException;

    List<StudentDto> getAllStudents();
}
