package com.spring.data.service;

import com.spring.data.excepttion.UserException;
import com.spring.data.model.StudentModel;

import java.util.List;

public interface StudentService
{
    StudentModel getStudent(Long studentId) throws UserException ;

    void saveStudent(StudentModel studentModel) throws UserException;

    void deleteStudent(Long studentId) throws UserException;

    void updateStudent(Long studentId, StudentModel studentModel) throws UserException;

    List<StudentModel> getAllStudents();
}
