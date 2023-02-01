package com.spring.data.config;

public enum UserPermission {
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    private String permission;

    UserPermission(final String permission)
    {
        this.permission = permission;
    }

    public String getPermission()
    {
        return permission;
    }
}
