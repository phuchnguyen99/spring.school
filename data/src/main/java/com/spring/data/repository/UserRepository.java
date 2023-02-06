package com.spring.data.repository;

import com.spring.data.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> selectApplicationUserByUsername(String username);
}
