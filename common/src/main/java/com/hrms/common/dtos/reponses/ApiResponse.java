package com.hrms.common.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int status_code;
    private boolean success;
    private String msg;
    private String token;
    private String role;
    private LocalDateTime timeStamp;

    public static ApiResponse success(int code, boolean success, String msg, String token, String role){
        return ApiResponse.builder()
                .status_code(code)
                .success(success)
                .msg(msg)
                .token(token)
                .role(role)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse error(int code, boolean success, String msg){
        return ApiResponse.builder()
                .status_code(code)
                .success(success)
                .msg(msg)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}