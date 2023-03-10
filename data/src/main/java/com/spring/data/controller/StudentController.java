package com.spring.data.controller;

import com.spring.data.converter.Converter;
import com.spring.data.dto.StudentRequest;
import com.spring.data.entity.Student;
import com.spring.data.excepttion.CourseException;
import com.spring.data.excepttion.UserException;
import com.spring.data.dto.StudentResponse;
import com.spring.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @Autowired
    private Converter converter;

    @GetMapping("/{studentId}")
    public StudentResponse getStudent(@PathVariable("studentId") Long studentId)
            throws UserException
    {
        final Student student = studentService.getStudent(studentId);
        return converter.convertStudentToStudentDto(student);
    }

    @PostMapping("/addStudent")
    @PreAuthorize("hasRole('ADMIN')")
    public void addStudent(@RequestBody StudentRequest studentDto)
            throws UserException
    {
        final Student student = converter.convertStudentRequestToStudent(studentDto);
        studentService.saveStudent(student);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Long studentId)
            throws UserException
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/updateStudent/{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                             @RequestBody StudentRequest studentDto) throws UserException
    {
        final Student student = converter.convertStudentRequestToStudent(studentDto);
        studentService.updateStudent(studentId, student);
    }

    @PostMapping("{studentId}/registerCourse/{courseId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public void registerCourse(@PathVariable("studentId") Long studentId,
                               @PathVariable("courseId") Long courseId)
            throws UserException, CourseException
    {
        studentService.registerCourse(studentId, courseId);
    }

    @DeleteMapping("{studentId}/removeCourse/{courseId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public void removeCourse(@PathVariable("studentId") Long studentId,
                             @PathVariable("courseId") Long courseId) throws UserException
    {
        studentService.removeCourse(studentId, courseId);
    }

    @GetMapping("/getAllStudents")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public List<StudentResponse> getAllStudents()
    {
        final List<Student> students = studentService.getAllStudents();
        return students.stream().map(s -> converter.convertStudentToStudentDto(s)).collect(Collectors.toList());
    }
}
