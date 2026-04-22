package com.hrms.hrCore.entity;

import com.hrms.hrCore.entity.LookupValues;
import com.hrms.hrCore.entity.Users;
import jakarta.persistence.*;
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
@Table(name = "jobs", schema = "core")
public class Jobs {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "job_code", nullable = false, unique = true)
    private String job_code;

    @Getter
    @Setter
    @Column(name = "job_title", nullable = false, unique = true)
    private String job_title;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false, foreignKey = @ForeignKey(name = "jobs_position_id_fkey"))
    private Positions positions;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_to_job_id", foreignKey = @ForeignKey(name = "jobs_reports_to_job_id_fkey"))
    private Jobs reports_toJob;

    @Getter
    @Setter
    @Column(name = "salary", precision = 14, scale = 2)
    private BigDecimal salary;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_type_id", foreignKey = @ForeignKey(name = "jobs_work_type_id_fkey"))
    private LookupValues workType;

    @Getter
    @Setter
    @Column(name = "contract_length")
    private String contract_length;

    @Getter
    @Setter
    @Column(name = "weekly_hours", precision = 5, scale = 2)
    private BigDecimal weekly_hours;

    @Getter
    @Setter
    @Column(name = "start_date", nullable = false)
    private OffsetDateTime start_date;

    @Getter
    @Setter
    @Column(name = "end_date")
    private OffsetDateTime end_date;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "jobs_state_id_fkey"))
    private LookupValues state;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "jobs_location_id_fkey"))
    private LookupValues location;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "jobs_company_id_fkey"))
    private LookupValues company;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "jobs_department_id_fkey"))
    private LookupValues department;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_centre_id", foreignKey = @ForeignKey(name = "jobs_cost_centre_id_fkey"))
    private LookupValues costCentre;

    @Getter
    @Setter
    @ColumnDefault("DRAFT")
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by", foreignKey = @ForeignKey(name = "jobs_approved_by_fkey"))
    private Users approvedBy;

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
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "created_by", nullable = false, foreignKey = @ForeignKey(name = "jobs_created_by_fkey"))
    private Users createdBy;

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
