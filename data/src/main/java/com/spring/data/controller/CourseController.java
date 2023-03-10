package com.spring.data.controller;

import com.spring.data.converter.Converter;
import com.spring.data.dto.CourseRequest;
import com.spring.data.dto.CourseResponse;
import com.spring.data.entity.Course;
import com.spring.data.excepttion.CourseException;
import com.spring.data.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public void addCourse(@RequestBody CourseRequest courseRequest) throws CourseException
    {
        final Course course = converter.convertCourseRequestToCourse(courseRequest);
        courseService.addCourse(course);
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('course:write', 'course:read')")
    public CourseResponse getCourse(@PathVariable("courseId") Long courseId) throws CourseException
    {
       final Course course = courseService.getCourse(courseId);
       return converter.convertCourseToCourseResponse(course);
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
                             @RequestBody CourseRequest courseRequest) throws CourseException
    {
        final Course course = converter.convertCourseRequestToCourse(courseRequest);
        courseService.modifyCourse(courseId, course);
    }
}
