package com.hrms.admin.projection;

import java.util.UUID;

public interface LookupValuesProjection {
    UUID getId();
    String getDisplayValue();
    String getCode();
    String getActive();
}

