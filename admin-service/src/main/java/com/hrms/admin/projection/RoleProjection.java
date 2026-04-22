package com.hrms.admin.projection;

import java.util.UUID;

public interface RoleProjection {
    UUID getId();
    String getName();
    String getDescription();
}