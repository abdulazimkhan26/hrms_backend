package com.hrms.admin.dto;

import lombok.Getter;

import java.util.UUID;

public class LookUpCategoryRequest {
    @Getter
    private UUID id;
    @Getter
    private String code;
    @Getter
    private String label;
    @Getter
    private String description;
}
