package com.spring.data.service;

import com.spring.data.entity.Course;
import com.spring.data.excepttion.CourseException;
import com.spring.data.excepttion.CourseNotFoundException;
import com.spring.data.repository.CourseRepository;
import com.spring.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addCourse(Course course) throws CourseException {
        final Optional<Course> foundCourse = courseRepository.findById(course.getCourseId());
        if(foundCourse.isPresent())
        {
            throw new CourseException("Course already existed");
        }
        courseRepository.save(course);
    }

    @Override
    public Course getCourse(Long courseId) throws CourseException {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course does not exist"));
    }

    @Override
    public void deleteCourse(Long courseId) throws CourseException {
        final Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course does not exist"));
        courseRepository.delete(foundCourse);
    }

    @Override
    public void modifyCourse(Long courseId, Course course) throws CourseException {
        final Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course does not exist"));
        if(course.getCourseName() != null && !course.getCourseName().isEmpty()
                && !foundCourse.getCourseName().equalsIgnoreCase(course.getCourseName()))
        {
            foundCourse.setCourseName(course.getCourseName());
        }
        if(course.getCourseCode() != null && !course.getCourseCode().isEmpty()
                && !foundCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode()))
        {
            foundCourse.setCourseId(course.getCourseId());
        }
        if(foundCourse.getTeacher() == null || !course.getTeacher().equals(foundCourse.getTeacher()))
        {
            foundCourse.setTeacher(course.getTeacher());
        }
        if(foundCourse.getCredit() != course.getCredit())
        {
            foundCourse.setCourseId(course.getCourseId());
        }
        courseRepository.save(foundCourse);
    }
}
