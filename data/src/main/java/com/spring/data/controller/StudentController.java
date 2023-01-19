package com.spring.data.controller;

import com.spring.data.excepttion.UserException;
import com.spring.data.model.StudentModel;
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
    public StudentModel getStudent(@PathVariable("studentId") Long studentId)
            throws UserException
    {
        return studentService.getStudent(studentId);
    }

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody StudentModel studentModel)
            throws UserException
    {
        studentService.saveStudent(studentModel);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId)
            throws UserException
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/updateStudent/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                             @RequestBody StudentModel studentModel) throws UserException
    {
        studentService.updateStudent(studentId, studentModel);
    }

    @GetMapping("/getAllStudents")
    public List<StudentModel> getAllStudents()
    {
        return studentService.getAllStudents();
    }
}
