package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "kpis", schema = "core")
public class Kpis {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "kpi_code", nullable = false, unique = true)
    private String kpi_code;

    @Getter
    @Setter
    @Column(name = "kpi_name", nullable = false)
    private String kpi_name;

    @Getter
    @Setter
    @Column(name = "kpi_description", nullable = false)
    private String kpi_description;

    @Getter
    @Setter
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn (name = "created_by", nullable = false, foreignKey = @ForeignKey(name = "kpis_created_by_fkey"))
    private Users users;

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
