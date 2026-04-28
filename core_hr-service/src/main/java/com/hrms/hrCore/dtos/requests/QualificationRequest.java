package com.hrms.hrCore.dtos.requests;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationRequest {
    private String qualificationCode;
    private String qualificationName;
    private String qualificationDescription;
    private UUID aqfLevelId;
    private UUID createdBy;
}
