package com.spring.data.controller;

import com.spring.data.dto.CourseDto;
import com.spring.data.excepttion.CourseException;
import com.spring.data.excepttion.UserException;
import com.spring.data.dto.StudentDto;
import com.spring.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public StudentDto getStudent(@PathVariable("studentId") Long studentId)
            throws UserException
    {
        return studentService.getStudent(studentId);
    }

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody StudentDto studentDto)
            throws UserException
    {
        studentService.saveStudent(studentDto);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId)
            throws UserException
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/updateStudent/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                             @RequestBody StudentDto studentDto) throws UserException
    {
        studentService.updateStudent(studentId, studentDto);
    }

    @PostMapping("{studentId}/registerCourse/{courseId}")
    public void registerCourse(@PathVariable("studentId") Long studentId,
                               @PathVariable("courseId") Long courseId)
            throws UserException, CourseException
    {
        studentService.registerCourse(studentId, courseId);
    }

    @DeleteMapping("{studentId}/removeCourse/{courseId}")
    public void removeCourse(@PathVariable("studentId") Long studentId,
                             @PathVariable("courseId") Long courseId) throws UserException
    {
        studentService.removeCourse(studentId, courseId);
    }

    @GetMapping("/getAllStudents")
    public List<StudentDto> getAllStudents()
    {
        return studentService.getAllStudents();
    }
}
