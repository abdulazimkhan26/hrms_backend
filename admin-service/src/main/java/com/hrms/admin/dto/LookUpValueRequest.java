package com.hrms.admin.dto;

import java.util.UUID;

import lombok.Getter;

public class LookUpValueRequest {
    @Getter
    private UUID id;
    @Getter
    private UUID categoryId;
    @Getter
    private String displayValue;
    @Getter
    private String code;
    @Getter
    private Boolean active;
}
