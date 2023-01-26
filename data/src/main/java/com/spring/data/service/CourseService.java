package com.spring.data.service;

import com.spring.data.entity.Course;
import com.spring.data.excepttion.CourseException;

public interface CourseService {
    void addCourse(Course course) throws CourseException;

    Course getCourse(Long courseId) throws CourseException;

    void deleteCourse(Long courseId) throws CourseException;

    void modifyCourse(Long courseId, Course courseDto) throws CourseException;
}
