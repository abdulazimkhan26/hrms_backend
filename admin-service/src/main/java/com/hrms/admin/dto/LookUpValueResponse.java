package com.hrms.admin.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

public class LookUpValueResponse {
    @Getter
    private String msg;
    @Getter
    private String code;
    @Getter
    private OffsetDateTime created_at;
    @Getter
    private boolean success;

    public LookUpValueResponse(String msg, String code, OffsetDateTime created_at, boolean success){
        this.msg = msg;
        this.code = code;
        this.created_at = created_at;
        this.success = success;
    }
}
