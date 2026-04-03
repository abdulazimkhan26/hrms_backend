package com.hrms.auth.service;

import com.hrms.auth.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

@Configuration
public class UserDetailsServiceImpl {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.hrms.auth.entity.User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Convert your entity to Spring Security UserDetails
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword_hash()) // hashed password
                    .authorities(Collections.emptyList()) // add roles if you have any
                    .build();
        };
    }
}