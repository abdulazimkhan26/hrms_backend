package com.hrms.admin.projection;

import java.util.UUID;

public interface LookupCategoryProjection {
    UUID getId();
    String getCode();
    String getLabel();
    String getDescription();
}

