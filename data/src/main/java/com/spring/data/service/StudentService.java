package com.spring.data.service;

import com.spring.data.entity.Student;
import com.spring.data.excepttion.CourseException;
import com.spring.data.excepttion.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService
{
    Student getStudent(Long studentId) throws UserException ;

    Student saveStudent(Student student) throws UserException;

    void deleteStudent(Long studentId) throws UserException;

    void updateStudent(Long studentId, Student student) throws UserException;

    List<Student> getAllStudents();

    void registerCourse(Long studentId, Long courseDto) throws UserException, CourseException;

    void removeCourse(Long studentId, Long courseId) throws UserException;
}
