package com.spring.data.service;

import com.spring.data.config.UserRole;
import com.spring.data.entity.User;
import com.spring.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userDao;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userDao.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("user not found"));
       user.setGrantedAuthorities(user.getRole().getGrantedAuthorities());
       return user;
    }
}
