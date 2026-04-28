package com.hrms.hrCore.dtos.requests;

import java.util.UUID;
import lombok.Data;

@Data
public class KpiRequest {
    private String kpiCode;
    private String kpiName;
    private String kpiDescription;
    private UUID createdBy;
}
