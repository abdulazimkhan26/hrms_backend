package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "position_kpis", schema = "core")
public class PositionKpis {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;    
    
    @ManyToOne
    @JoinColumn(name = "position_id", foreignKey = @ForeignKey(name = "position_kpis_position_id_fkey"), nullable = false)
    private Positions positions;    
    
    @ManyToOne
    @JoinColumn(name = "kpi_id", foreignKey = @ForeignKey(name = "position_kpis_kpi_id_fkey"), nullable = false)
    private Kpis kpi;    
    
    @Check(constraints = "weighting >= 0 AND weighting <= 100")
    @Column(name = "weighting", precision = 3, scale = 2, nullable = false)
    private BigDecimal weighting;  
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
