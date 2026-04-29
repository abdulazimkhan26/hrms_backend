package com.hrms.hrCore.dtos.requests;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;
import com.hrms.hrCore.entity.Positions;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionRequest {
    private String positionCode;
    private String positionName;
    private String positionDescription;
    private UUID positionLevelId;  
    private UUID workTypeId;       
    private BigDecimal salaryMinimum;
    private BigDecimal salaryMaximum;
    private String contractLength;
    private BigDecimal weeklyHoursMin;
    private BigDecimal weeklyHoursMax;
    private UUID reportsToId;         
    private Boolean active;
    private String status;  
    private String createdBy;             
}