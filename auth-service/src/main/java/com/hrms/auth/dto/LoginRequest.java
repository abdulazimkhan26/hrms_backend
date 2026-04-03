package com.hrms.auth.dto;

import lombok.Getter;

public class LoginRequest {
    @Getter
    private boolean success;
    @Getter
    private String username;
    @Getter
    private String password;
}
