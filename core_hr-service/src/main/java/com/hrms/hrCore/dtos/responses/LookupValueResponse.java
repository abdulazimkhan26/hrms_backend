package com.hrms.hrCore.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

// dto/response/LookupValueDTO.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupValueResponse {
    private UUID id;
    private String code;
    private String display_value;
}