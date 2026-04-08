package com.hrms.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.generator.EventType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "roles", schema = "core")
public class Roles {
    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "description", nullable = false)
    private String description;

    @Getter
    @Setter
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private OffsetDateTime created_at;
}
