package com.hrms.hrCore.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class JobsRequest {
    private String jobCode;
    private String jobTitle;
    private UUID positionId;
    private UUID reportsToJobId;
    private BigDecimal salary;
    private UUID workTypeId;
    private String contractLength;
    private BigDecimal weeklyHours;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID stateId;
    private UUID locationId;
    private UUID companyId;
    private UUID departmentId;
    private UUID costCentreId;
    private String status;
    private UUID approvedBy;
    private LocalDateTime approved_at;
    private String declineReason;
    private Boolean active;
    private UUID createdBy;
}
