package com.hrms.hrCore.dtos.requests;
import java.util.UUID;
import lombok.Data;

@Data
public class CourseServiceProvidersRequest {
    private String providerNumber;
    private String name;
    private String contactPerson;
    private String telephone;
    private String address;
    private String email;
    private UUID createdBy;
    private Boolean active;
}
