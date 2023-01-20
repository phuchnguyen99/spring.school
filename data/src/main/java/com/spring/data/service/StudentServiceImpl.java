package com.spring.data.service;

import com.spring.data.converter.Converter;
import com.spring.data.entity.Student;
import com.spring.data.excepttion.UserException;
import com.spring.data.excepttion.UserNotFoundException;
import com.spring.data.dto.StudentDto;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Converter converter;

    @Override
    @Transactional
    public StudentDto getStudent(Long studentId) throws UserNotFoundException {
        final Student student = retrieveStudent(studentId);
        return converter.convertStudentToStudentDto(student);
    }

    @Override
    @Transactional
    public void saveStudent(StudentDto studentDto) throws UserException {
        final Student student = converter.convertStudentDtoToStudent(studentDto);
        final Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if(studentOptional.isPresent())
        {
            throw new UserException("Student already exists.");
        }
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) throws UserException {
        final Student student = retrieveStudent(studentId);
        studentRepository.delete(student);
    }

    @Override
    @Transactional
    public void updateStudent(Long studentId, StudentDto studentDto) throws UserException {
        final Student student = retrieveStudent(studentId);
        if(studentDto.getFirstName() != null && !student.getFirstName().equalsIgnoreCase(studentDto.getFirstName()))
        {
            student.setFirstName(studentDto.getFirstName());
        }
        if(studentDto.getLastName() != null && !student.getLastName().equalsIgnoreCase(studentDto.getLastName()))
        {
            student.setLastName(studentDto.getLastName());
        }
        if(studentDto.getEmail() != null && !student.getLastName().equalsIgnoreCase(studentDto.getLastName()))
        {
            student.setEmail(studentDto.getEmail());
        }
        studentRepository.save(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
       return studentRepository.findAll().stream()
                .map(s -> converter.convertStudentToStudentDto(s)
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
