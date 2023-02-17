package com.spring.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    List<CourseResponse> courseResponseList = new ArrayList<>();
}
