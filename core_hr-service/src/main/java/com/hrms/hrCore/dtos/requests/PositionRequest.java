package com.hrms.hrCore.dtos.requests;


import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionRequest {
    private String positionCode;
    private String positionName;
    private String positionDescription;
    private String positionLevelCode;   // ← client sends "SENIOR" not UUID
    private String workTypeCode;        // ← client sends "FULL_TIME" not UUID
    private BigDecimal salaryMinimum;
    private BigDecimal salaryMaximum;
    private String contractLength;
    private BigDecimal weeklyHoursMin;
    private BigDecimal weeklyHoursMax;
    private String reportsToId;           // ← same DB, send UUID directly
    private String createdBy;             // ← admin-service user UUID
}