package com.hrms.auth.service;

import com.hrms.auth.dto.LoginRequest;
import com.hrms.auth.dto.LoginResponse;
import com.hrms.auth.entity.User;
import com.hrms.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if(optionalUser.isEmpty()){
            return new LoginResponse(null, "Username doesn't exist! Try with a valid username.", null, null, false);
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword_hash())) {
            return new LoginResponse(null, "Invalid password! Try again.", null, null, false);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("username", user.getUsername());


        String token = jwtService.generateToken(user.getUsername(), claims);

        String role = jwtService.returnClaims(token);

        return new LoginResponse(user.getUsername(), "Login Successful!", token, role, true);
    }
}