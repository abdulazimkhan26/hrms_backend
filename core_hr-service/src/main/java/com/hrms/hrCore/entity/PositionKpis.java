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
@Table(name = "position_kpis", schema = "core")
public class PositionKpis {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "position_id", foreignKey = @ForeignKey(name = "position_kpis_position_id_fkey"), nullable = false)
    private Positions positions;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "kpi_id", foreignKey = @ForeignKey(name = "position_kpis_kpi_id_fkey"), nullable = false)
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
