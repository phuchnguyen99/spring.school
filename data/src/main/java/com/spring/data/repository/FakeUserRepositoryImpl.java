package com.spring.data.repository;

import com.spring.data.config.UserRole;
import com.spring.data.entity.User;
import com.spring.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("fakeUserRepo")
public class FakeUserRepositoryImpl implements UserRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> selectApplicationUserByUsername(String username) {
        return getUsers().stream().filter( user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

        public List<User> getUsers(){
                return Arrays.asList(
                            new User("phuc", passwordEncoder.encode("phuc1"), "phuc@gmail.com", UserRole.STUDENT,
                                            UserRole.STUDENT.getGrantedAuthorities(), true,
                                            true, true, true),
                            new User("ben", passwordEncoder.encode("ben1"), "ben@gmail.com", UserRole.TEACHER,
                                            UserRole.TEACHER.getGrantedAuthorities(), true,
                                           true, true, true),
                            new User("ngoc", passwordEncoder.encode("ngoc1"), "ngoc@gmail.com", UserRole.ADMIN,
                                            UserRole.ADMIN.getGrantedAuthorities(), true,
                                            true, true, true)
                            );
            }
}
