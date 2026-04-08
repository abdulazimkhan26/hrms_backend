package com.hrms.admin.dto;

import lombok.Getter;

import java.time.OffsetDateTime;

public class LookUpCategoryResponse {
    @Getter
    private String msg;
    @Getter
    private String code;
    @Getter
    private String label;
    @Getter
    private OffsetDateTime created_at;
    @Getter
    private boolean success;

    public LookUpCategoryResponse(String msg, String code, String label, OffsetDateTime created_at, boolean success){
        this.msg = msg;
        this.code = code;
        this.label = label;
        this.created_at = created_at;
        this.success = success;
    }

}
