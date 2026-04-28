package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jobs", schema = "core")
public class Jobs {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "job_code", nullable = false, unique = true)
    private String jobCode;

    @Column(name = "job_title", nullable = false, unique = true)
    private String jobTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false, foreignKey = @ForeignKey(name = "jobs_position_id_fkey"))
    private Positions positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_to_job_id", foreignKey = @ForeignKey(name = "jobs_reports_to_job_id_fkey"))
    private Jobs reportsToJobId;

    @Column(name = "salary", precision = 14, scale = 2)
    private BigDecimal salary;

    @Column(name = "work_type_id", nullable=false)
    private UUID workTypeId;

    @Column(name = "contract_length")
    private String contractLength;

    @Column(name = "weekly_hours", precision = 5, scale = 2)
    private BigDecimal weeklyHours;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "state_id")
    private UUID stateId;

    @Column(name = "location_id")
    private UUID locationId;

    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "department_id")
    private UUID departmentId;

    @Column(name = "cost_centre_id")
    private UUID costCentreId;

    @ColumnDefault("DRAFT")
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "approved_by")
    private UUID approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approved_at;

    @Column(name = "decline_reason")
    private String declineReason;

    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column (name = "created_by", nullable = false)
    private UUID createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;
}
