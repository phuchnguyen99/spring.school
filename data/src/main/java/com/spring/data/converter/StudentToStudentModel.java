package com.spring.data.converter;

import com.spring.data.entity.Student;
import com.spring.data.model.StudentModel;

public class StudentToStudentModel
{
    public static Student convertStudentToStudentModel(final StudentModel studentModel)
    {
        final Student student = new Student();
        student.setId(studentModel.getStudentId());
        student.setFirstName(studentModel.getFirstName());
        student.setLastName(studentModel.getLastName());
        student.setEmail(studentModel.getEmail());
        return student;
    }
}
