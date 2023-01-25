package com.spring.data.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;

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
    @Email
    private String email;
}
