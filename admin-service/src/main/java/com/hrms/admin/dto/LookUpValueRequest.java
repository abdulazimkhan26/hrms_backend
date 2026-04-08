package com.hrms.admin.dto;

import lombok.Getter;

public class LookUpValueRequest {
    @Getter
    private String categoryCode;
    @Getter
    private String displayValue;
    @Getter
    private String code;
    @Getter
    private boolean is_active;
}
