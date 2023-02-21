package com.spring.data.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.spring.data.config.UserPermission.*;

public enum UserRole {
    STUDENT(new HashSet<>(Arrays.asList(STUDENT_READ)), "student"),
    TEACHER(new HashSet<>(Arrays.asList(STUDENT_READ, COURSE_WRITE, COURSE_READ)), "teacher"),
    ADMIN(new HashSet<>(Arrays.asList(STUDENT_WRITE, STUDENT_READ, COURSE_READ, COURSE_WRITE)),
            "admin");

    private Set<UserPermission> userPermissions;
    private String name;

    UserRole(final Set<UserPermission> userPermissions, final String name)
    {
        this.userPermissions = userPermissions;
        this.name = name;
    }

    public Set<UserPermission> getUserPermissions()
    {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = getUserPermissions()
                        .stream()
                        .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                        .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
    public static UserRole values(String name){
        return Arrays.asList(UserRole.values()).stream()
                .filter(userRole -> userRole.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(UserRole.STUDENT);
    }
}
