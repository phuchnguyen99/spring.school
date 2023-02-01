package com.spring.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private String username;

    @Column(length = 60)
    private String password;
    private String role;
    private String email;
    private boolean enabled;
}
