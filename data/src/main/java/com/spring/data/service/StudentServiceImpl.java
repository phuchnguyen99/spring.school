package com.spring.data.service;

import com.spring.data.converter.StudentToStudentModel;
import com.spring.data.entity.Student;
import com.spring.data.excepttion.UserException;
import com.spring.data.excepttion.UserNotFoundException;
import com.spring.data.model.StudentModel;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentModel getStudent(Long studentId) throws UserNotFoundException {
        final Student student = retrieveStudent(studentId);
        return StudentModel.builder()
                .studentId(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }

    @Override
    public void saveStudent(StudentModel studentModel) throws UserException {
        final Student student = StudentToStudentModel.convertStudentToStudentModel(studentModel);
        final Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if(studentOptional.isPresent())
        {
            throw new UserException("Student already exists.");
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) throws UserException {
        final Student student = retrieveStudent(studentId);
        studentRepository.delete(student);
    }

    @Override
    public void updateStudent(Long studentId, StudentModel studentModel) throws UserException {
        final Student student = retrieveStudent(studentId);
        if(studentModel.getFirstName() != null && !student.getFirstName().equalsIgnoreCase(studentModel.getFirstName()))
        {
            student.setFirstName(studentModel.getFirstName());
        }
        if(studentModel.getLastName() != null && !student.getLastName().equalsIgnoreCase(studentModel.getLastName()))
        {
            student.setLastName(studentModel.getLastName());
        }
        if(studentModel.getEmail() != null && !student.getLastName().equalsIgnoreCase(studentModel.getLastName()))
        {
            student.setEmail(studentModel.getEmail());
        }
        studentRepository.save(student);
    }

    @Override
    public List<StudentModel> getAllStudents() {
       return studentRepository.findAll().stream()
                .map(student -> new StudentModel().builder()
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .email(student.getEmail()).build()
                ).collect(Collectors.toList());
    }

    private Student retrieveStudent(final Long studentId) throws UserNotFoundException
    {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    return new UserNotFoundException("Student does not exist.");
                });
    }

}
