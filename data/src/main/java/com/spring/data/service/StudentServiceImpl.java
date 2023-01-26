package com.spring.data.service;

import com.spring.data.converter.Converter;
import com.spring.data.dto.CourseDto;
import com.spring.data.entity.Course;
import com.spring.data.entity.Student;
import com.spring.data.excepttion.CourseException;
import com.spring.data.excepttion.CourseNotFoundException;
import com.spring.data.excepttion.UserException;
import com.spring.data.excepttion.UserNotFoundException;
import com.spring.data.dto.StudentDto;
import com.spring.data.repository.CourseRepository;
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
    private CourseRepository courseRepository;

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
        final Student student = (Student) converter.convertEntityDto(studentDto, new Student());
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
