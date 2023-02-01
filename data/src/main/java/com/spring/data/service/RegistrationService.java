package com.spring.data.service;

import com.spring.data.dto.UserDto;
import com.spring.data.entity.User;

public interface RegistrationService {
    User register(UserDto userDto);
}
