package com.spring.data.config;

import com.spring.data.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.spring.data.config.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter  {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(final PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService()
    {
        final UserDetails phucUser = User.builder()
                .username("phuc")
                .password(passwordEncoder.encode("phuc1"))
                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        final UserDetails benUser = User.builder()
                .username("ben")
                .password(passwordEncoder.encode("ben1"))
                .roles(TEACHER.name())
                .authorities(TEACHER.getGrantedAuthorities())
                .build();
        final UserDetails ngocUser = User.builder()
                .username("ngoc")
                .password(passwordEncoder.encode("ngoc1"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(phucUser, benUser, ngocUser);
    }


}
