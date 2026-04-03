package com.hrms.auth.controllers;

import com.hrms.auth.dto.LoginRequest;
import com.hrms.auth.dto.LoginResponse;
import com.hrms.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.common.dtos.reponses.ApiResponse;

@RestController
@RequestMapping("/auth")   // base path for all auth APIs
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.loginUser(request);

        if (!response.isSuccess()) {
            // wrap whole LoginResponse (includes token, msg, etc.)
            return ResponseEntity.status(401)
                    .body(ApiResponse.error(401, false, response.getMsg()));
        }

        // wrap whole LoginResponse for success too
        return ResponseEntity.ok(ApiResponse.success(200, true, response.getMsg(), response.getToken(), response.getRole()));
    }
}