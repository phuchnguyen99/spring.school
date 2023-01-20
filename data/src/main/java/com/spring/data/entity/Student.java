package com.spring.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student")
public class Student extends User{
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            )
    )
    List<Course> courseList = new ArrayList<>();

    public void addCourse(final Course course)
    {
        if(!courseList.contains(course))
        {
            courseList.add(course);
        }
    }
}
