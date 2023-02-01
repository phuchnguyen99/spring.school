package com.spring.data.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.spring.data.config.UserPermission.*;

public enum UserRole {
    STUDENT(new HashSet<>(Arrays.asList(STUDENT_READ))),
    TEACHER(new HashSet<>(Arrays.asList(STUDENT_READ, COURSE_WRITE, COURSE_READ))),
    ADMIN(new HashSet<>(Arrays.asList(STUDENT_WRITE, STUDENT_READ, COURSE_READ, COURSE_WRITE)));

    private Set<UserPermission> userPermissions;

    UserRole(final Set<UserPermission> userPermissions)
    {
        this.userPermissions = userPermissions;
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
}
