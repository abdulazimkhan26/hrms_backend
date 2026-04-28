package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees", schema = "core")
public class Employees {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_id", unique = true)
    private UUID userId;

    @Column(name = "employee_number", length = 30, nullable = false, unique = true)
    private String employeeNumber;

    @Column(name = "job_id")
    private UUID jobId;

    @Column(length = 20)
    private String title;

    @Column(length = 10)
    private String initials;

    @Column(name = "full_names", length = 150, nullable = false)
    private String fullNames;

    @Column(length = 100, nullable = false)
    private String surname;

    @Column(name = "preferred_name", length = 100)
    private String preferredName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender_id")
    private UUID genderId;

    @Column(name = "marital_status_id")
    private UUID maritalStatusId;

    @Column(name = "children")
    @ColumnDefault("0")
    private Integer children;

    @Column(name = "drivers_licence_no", length = 50)
    private String driversLicenceNo;

    @Column(name = "address_line1", length = 200)
    private String addressLine1;

    @Column(name = "address_line2", length = 200)
    private String addressLine2;

    @Column(name = "suburb_city", length = 100)
    private String suburbCity;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "state_id")
    private UUID stateId;

    @Column(name = "home_phone", length = 30)
    private String homePhone;

    @Column(name = "mobile_phone", length = 30)
    private String mobilePhone;

    @Column(name = "email_address", length = 200)
    private String emailAddress;

    @Column(name = "pay_method_id")
    private UUID payMethodId;

    @Column(name = "pay_frequency_id")
    private UUID payFrequencyId;

    @Column(name = "employment_type_id")
    private UUID employmentTypeId;

    @Column(name = "employment_code", length = 30)
    private String employmentCode;

    @Column(name = "commencement_date")
    private LocalDate commencementDate;

    @Column(nullable = false, length = 30)
    @ColumnDefault("'ACTIVE'")
    private String status;

    @Column(name = "created_by")
    private UUID createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}