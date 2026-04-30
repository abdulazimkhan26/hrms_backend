package com.hrms.hrCore.dtos.requests;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class PositionKpisRequest {
    private UUID positions;    
    private UUID kpi;    
    private BigDecimal weighting;  
}
