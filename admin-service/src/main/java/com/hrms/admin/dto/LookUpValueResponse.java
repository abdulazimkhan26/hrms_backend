package com.hrms.admin.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class LookUpValueResponse {
    @Getter
    private String msg;
    @Getter
    private OffsetDateTime created_at;
    @Getter
    private boolean success;
    @Getter
    private UUID id;

    public LookUpValueResponse(String msg, OffsetDateTime created_at, boolean success, UUID id){
        this.msg = msg;
        this.created_at = created_at;
        this.success = success;
        this.id = id;
    }
}
