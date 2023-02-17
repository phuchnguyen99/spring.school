package com.spring.data.service;

import com.spring.data.config.UserRole;
import com.spring.data.dto.UserDto;
import com.spring.data.entity.Student;
import com.spring.data.entity.User;
import com.spring.data.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class RegsitrationServiceImpl implements RegistrationService{
    private final StudentRepository studentRepository;

    public RegsitrationServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public User register(UserDto userDto) {
        return null;
    }
}
