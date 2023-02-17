package com.spring.data.entity;

import com.spring.data.config.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Set;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails
{
    private String username;

    @Column(length = 60)
    private String password;
    private String email;
    private UserRole role;
    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;
     private boolean isAccountNonExpired = true;
     private boolean isAccountNonLocked = true;
     private boolean isCredentialsNonExpired = true;
     private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
}
