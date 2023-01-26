package com.spring.data.service;

import com.spring.data.converter.Converter;
import com.spring.data.entity.Course;
import com.spring.data.entity.Student;
import com.spring.data.excepttion.CourseException;
import com.spring.data.excepttion.CourseNotFoundException;
import com.spring.data.excepttion.UserException;
import com.spring.data.excepttion.UserNotFoundException;
import com.spring.data.repository.CourseRepository;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private Converter converter;

    @Override
    @Transactional
    public Student getStudent(Long studentId) throws UserNotFoundException {
       return retrieveStudent(studentId);
    }

    @Override
    @Transactional
    public void saveStudent(Student student) throws UserException {
        final Optional<Student> studentOptional = studentRepository.findById(student.getStudentId());
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
    public void updateStudent(Long studentId, Student student) throws UserException {
        final Student foundStudent = retrieveStudent(studentId);
        if(student.getFirstName() != null && !student.getFirstName().equalsIgnoreCase(foundStudent.getFirstName()))
        {
            foundStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName() != null && !student.getLastName().equalsIgnoreCase(foundStudent.getLastName()))
        {
            foundStudent.setLastName(student.getLastName());
        }
        if(student.getEmail() != null && !student.getLastName().equalsIgnoreCase(foundStudent.getLastName()))
        {
            foundStudent.setEmail(student.getEmail());
        }
        studentRepository.save(foundStudent);
    }

    @Override
    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    @Override
    public void registerCourse(Long studentId, Long courseId) throws UserException, CourseException{
        final Student student = retrieveStudent(studentId);
        final Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course does not exists"));
        if(!student.getCourseList().contains(course)) {
            student.addCourse(course);
            studentRepository.save(student);
        }
        else {
            throw new UserException("Student already registered the course");
        }
    }

    @Override
    public void removeCourse(Long studentId, Long courseId) throws UserException {
        final Student student = retrieveStudent(studentId);
        final Course course = student.getCourseList().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findAny().orElseThrow( () -> new UserException("Course is not existed"));
        student.getCourseList().remove(course);
        studentRepository.save(student);
        courseRepository.save(course);

    }

    private Student retrieveStudent(final Long studentId) throws UserNotFoundException
    {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    return new UserNotFoundException("Student does not exist.");
                });
    }
}
