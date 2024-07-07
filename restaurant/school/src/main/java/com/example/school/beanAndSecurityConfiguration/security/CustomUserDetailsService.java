package com.example.school.beanAndSecurityConfiguration.security;

import com.example.school.SchoolLoginService;
import com.example.school.dto.SchoolLogin;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailsService implements UserDetailsService {
    private final SchoolLoginService schoolLoginService;

    public CustomUserDetailsService(SchoolLoginService schoolLoginService) {
        this.schoolLoginService = schoolLoginService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return schoolLoginService.login(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    private UserDetails createUserDetails(SchoolLogin login) {
        return User.builder()
                .username(login.email())
                .password(login.password())
                //.roles(credentials.roles().toArray(String[]::new))
                .build();
    }
}

