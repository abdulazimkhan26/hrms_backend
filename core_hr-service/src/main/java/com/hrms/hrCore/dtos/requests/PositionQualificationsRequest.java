package com.hrms.hrCore.dtos.requests;

import java.util.UUID;
import lombok.Data;

@Data
public class PositionQualificationsRequest {
    private UUID id;
    private UUID positionId;
    private UUID qualificationId;
    private String requirementType;
}
