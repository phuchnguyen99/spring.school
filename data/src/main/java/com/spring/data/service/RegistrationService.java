package com.spring.data.service;

import com.spring.data.dto.UserRequest;
import com.spring.data.entity.User;

public interface RegistrationService {
    User register(UserRequest userRequest);
}
