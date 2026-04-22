package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_kpis", schema = "core")
public class JobKpis {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "job_kpis_job_id_fkey"), nullable = false)
    private Jobs job;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "kpi_id", foreignKey = @ForeignKey(name = "job_kpis_kpi_id_fkey"), nullable = false)
    private Kpis kpi;

    @Getter
    @Setter
    @Check(constraints = "weighting >= 0 AND weighting <= 100")
    @Column(name = "weighting", precision = 3, scale = 2, nullable = false)
    private BigDecimal weighting;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;
}
