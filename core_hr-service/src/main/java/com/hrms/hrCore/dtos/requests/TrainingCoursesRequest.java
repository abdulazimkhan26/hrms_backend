package com.hrms.hrCore.dtos.requests;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class TrainingCoursesRequest {
    private String courseCode;
    private String courseName;
    private String courseDescription;
    private UUID aqfLevelId;
    private UUID categoryId;
    private UUID providerId;
    private BigDecimal cost;
    private UUID createdBy;
}
