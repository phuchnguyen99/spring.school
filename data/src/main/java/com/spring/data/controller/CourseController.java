package com.spring.data.controller;

import com.spring.data.converter.Converter;
import com.spring.data.dto.CourseDto;
import com.spring.data.entity.Course;
import com.spring.data.excepttion.CourseException;
import com.spring.data.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private Converter converter;

    @PostMapping("/addCourse")
    @PreAuthorize("hasAnyAuthority('course:write')")
    public void addCourse(@RequestBody CourseDto courseDto) throws CourseException
    {
        final Course course = converter.convertCourseDtoToCourse(courseDto);
        courseService.addCourse(course);
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('course:write', 'course:read')")
    public CourseDto getCourse(@PathVariable("courseId") Long courseId) throws CourseException
    {
       final Course course = courseService.getCourse(courseId);
       return converter.convertCourseToCourseDto(course);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    @PreAuthorize("hasAnyAuthority('course:write')")
    public void deleteCourse(@PathVariable("courseId") Long courseId) throws CourseException
    {
        courseService.deleteCourse(courseId);
    }

    @PutMapping("/modifyCourse/{courseId}")
    @PreAuthorize("hasAnyAuthority('course:write')")
    public void modifyCourse(@PathVariable("courseId") Long courseId,
                             @RequestBody CourseDto courseDto) throws CourseException
    {
        final Course course = converter.convertCourseDtoToCourse(courseDto);
        courseService.modifyCourse(courseId, course);
    }
}
