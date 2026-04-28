package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "positions", schema = "core")
@Builder
public class Positions {    
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "position_code", nullable = false, unique = true)
    private String positionCode;

    @Column(name = "position_name", nullable = false)
    private String positionName;

    @Column(name = "position_description")
    private String positionDescription;

    @Column(name = "position_level_id", nullable = false)
    private UUID positionLevelId;

    @Column(name = "salary_minimum", precision = 14, scale = 2)
    private BigDecimal salaryMinimum;

    @Column(name = "salary_maximum", precision = 14, scale = 2)
    private BigDecimal salaryMaximum;

    @Column(name = "work_type_id", nullable = false)
    private UUID workTypeId;

    @Column(name = "contract_length")
    private String contractLength;

    @Column(name = "weekly_hours_min")
    private BigDecimal weeklyHoursMin;

    @Column(name = "weekly_hours_max")
    private BigDecimal weeklyHoursMax;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "reports_to_id")
    private UUID reportsTo;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "approved_by")
    private UUID approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "decline_reason")
    private String declineReason;

    @Column (name = "created_by", nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
