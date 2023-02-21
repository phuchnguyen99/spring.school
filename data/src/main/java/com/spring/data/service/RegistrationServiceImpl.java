package com.spring.data.service;

import com.spring.data.config.UserRole;
import com.spring.data.dto.UserRequest;
import com.spring.data.entity.User;
import com.spring.data.repository.StudentRepository;
import com.spring.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRequest userRequest) {
       final Optional<User> savedUser = userRepository.findByUsername(userRequest.getUsername());
       if(savedUser.isPresent())
       {
           throw new IllegalStateException("User already exist");
       }
        final User user = new User();
        user.setId(new Random().nextLong());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(UserRole.values(userRequest.getRole()));
        return userRepository.save(user);
    }
}
