package com.hrms.auth.dto;

import lombok.Getter;

import java.util.UUID;

public class LoginResponse {
    @Getter
    private boolean success;
    @Getter
    private String username;
    @Getter
    private String msg;
    @Getter
    private String token;
    @Getter
    private String role;
    @Getter
    private UUID user_ID;

    public LoginResponse(String username, String msg, String token, UUID user_ID, String role, boolean success) {
        this.success = success;
        this.username = username;
        this.msg = msg;
        this.token = token;
        this.role = role;
        this.user_ID = user_ID;
    }
}
