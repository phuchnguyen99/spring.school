package com.spring.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher extends User
{
    String department;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_course_map",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            )
    )
    List<Course> courseList;
}
