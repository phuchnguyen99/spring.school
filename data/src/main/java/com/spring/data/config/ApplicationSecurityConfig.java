package com.spring.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter  {
    private PasswordEncoder passwordEncoder;

    private final UserDetailsService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(final PasswordEncoder passwordEncoder,
                                     @Qualifier("userDetailsServiceImpl") final UserDetailsService applicationUserService)
    {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "index", "/css/*", "/js/*", "/templates")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                        .loginPage("/login").permitAll()
                        .failureUrl("/error")
                        .defaultSuccessUrl("/success")
                        .passwordParameter("password")
                        .usernameParameter("username")
                .and()
                .rememberMe()
                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                        .key("securedkey")
                .and()
                .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessUrl("/login");
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return applicationUserService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }
}
