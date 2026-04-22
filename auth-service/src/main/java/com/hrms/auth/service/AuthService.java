package com.hrms.auth.service;

import com.hrms.auth.dto.LoginRequest;
import com.hrms.auth.dto.LoginResponse;
import com.hrms.auth.entity.Roles;
import com.hrms.auth.entity.User;
import com.hrms.auth.repository.RolesRepository;
import com.hrms.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if(optionalUser.isEmpty()){
            return new LoginResponse(null, "Username doesn't exist! Try with a valid username.", null, null,null, false);
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword_hash())) {
            return new LoginResponse(null, "Invalid password! Try again.", null,null, null, false);
        }

        Optional<Roles> roles = rolesRepository.findById(user.getRole());

        String role_name = roles
                .map(Roles::getName)
                .orElse(null);

        UUID user_ID = user.getId();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role_name);
        claims.put("userID", user_ID);
        claims.put("username", user.getUsername());


        String token = jwtService.generateToken(user.getUsername(), claims);
        String role = jwtService.returnClaims(token);

        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);

        return new LoginResponse(user.getUsername(), "Login Successful!", token, user_ID, role, true);
    }
}