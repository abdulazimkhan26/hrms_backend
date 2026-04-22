package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "positions", schema = "core")
@Builder
public class Positions {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "position_code", nullable = false, unique = true)
    private String positionCode;

    @Getter
    @Setter
    @Column(name = "position_name", nullable = false)
    private String positionName;

    @Getter
    @Setter
    @Column(name = "position_description")
    private String positionDescription;

    @Getter
    @Setter
    @Column(name = "position_level_id", nullable = false)
    private UUID positionLevelId;

    @Getter
    @Setter
    @Column(name = "salary_minimum", precision = 14, scale = 2)
    private BigDecimal salaryMinimum;

    @Getter
    @Setter
    @Column(name = "salary_maximum", precision = 14, scale = 2)
    private BigDecimal salaryMaximum;

    @Getter
    @Setter
    @JoinColumn(name = "work_type_id", nullable = false)
    private UUID workTypeId;

    @Getter
    @Setter
    @Column(name = "contract_length")
    private String contractLength;

    @Getter
    @Setter
    @Column(name = "weekly_hours_min")
    private BigDecimal weeklyHoursMin;

    @Getter
    @Setter
    @Column(name = "weekly_hours_max")
    private BigDecimal weeklyHoursMax;

    @Getter
    @Setter
    @ColumnDefault("DRAFT")
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_to_id")
    private Positions reportsTo;

    @Getter
    @Setter
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Getter
    @Setter
    @JoinColumn(name = "approved_by")
    private UUID approved_by;

    @Getter
    @Setter
    @Column(name = "approved_at")
    private OffsetDateTime approved_at;

    @Getter
    @Setter
    @Lob
    @Column(name = "decline_reason")
    private String decline_reason;

    @Getter
    @Setter
    @Column (name = "created_by", nullable = false)
    private String created_by;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;
}
