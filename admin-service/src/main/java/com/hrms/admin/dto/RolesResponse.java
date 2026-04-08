package com.hrms.admin.dto;

import lombok.Getter;
import lombok.Setter;

public class RolesResponse {
    @Getter
    private String msg;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private boolean success;

    public RolesResponse(String msg, String name, String description, boolean success){
        this.msg = msg;
        this.name = name;
        this.description = description;
        this.success = success;
    }
}
