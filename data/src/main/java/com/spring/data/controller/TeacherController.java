package com.spring.data.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//todo: implement teacher controller
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @PostMapping("/registerTeacher")
    public void addTeacher()
    {

    }
}


