package com.spring.data.entity;

import com.spring.data.config.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@Entity
@Setter
@Getter
public class User implements UserDetails
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long Id;
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

    public User(String username, String password, String email, UserRole role, Set<? extends GrantedAuthority> grantedAuthorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        this.grantedAuthorities = Collections.unmodifiableSet(new HashSet<>(authorities));
    }
}
