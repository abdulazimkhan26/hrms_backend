package com.hrms.admin.entity;

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
@Table(name = "positions", schema = "core")
public class Positions {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "position_code", nullable = false, unique = true)
    private String position_code;

    @Getter
    @Setter
    @Column(name = "position_name", nullable = false)
    private String position_name;

    @Getter
    @Setter
    @Column(name = "position_description")
    private String position_description;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "position_level_id", nullable = false, foreignKey = @ForeignKey(name = "positions_position_level_id_fkey"))
    private LookupValues lkv;

    @Getter
    @Setter
    @Column(name = "salary_minimum", precision = 14, scale = 2)
    private BigDecimal salary_minimum;

    @Getter
    @Setter
    @Column(name = "salary_maximum", precision = 14, scale = 2)
    private BigDecimal salary_maximum;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "work_type_id", nullable = false, foreignKey = @ForeignKey(name = "positions_work_type_id_fkey"))
    private LookupValues lkv1;

    @Getter
    @Setter
    @Column(name = "contract_length")
    private String contract_length;

    @Getter
    @Setter
    @Column(name = "weekly_hours_min")
    private BigDecimal weekly_hours_min;

    @Getter
    @Setter
    @Column(name = "weekly_hours_max")
    private BigDecimal weekly_hours_max;

    @Getter
    @Setter
    @ColumnDefault("DRAFT")
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "reports_to_id", foreignKey = @ForeignKey(name = "positions_reports_to_id_fkey"))
    private Positions position;

    @Getter
    @Setter
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by", foreignKey = @ForeignKey(name = "positions_approved_by_fkey"))
    private Users users;

    @Getter
    @Setter
    @Column(name = "approved_at", nullable = false)
    private OffsetDateTime approved_at;

    @Getter
    @Setter
    @Lob
    @Column(name = "decline_reason")
    private String decline_reason;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "created_by", nullable = false, foreignKey = @ForeignKey(name = "positions_created_by_fkey"))
    private Users users1;

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
