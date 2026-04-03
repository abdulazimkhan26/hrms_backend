package com.hrms.auth.dto;

import lombok.Getter;

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

    public LoginResponse(String username, String msg, String token, String role, boolean success) {
        this.success = success;
        this.username = username;
        this.msg = msg;
        this.token = token;
        this.role = role;
    }
}
