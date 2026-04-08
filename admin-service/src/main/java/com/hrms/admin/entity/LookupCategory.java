package com.hrms.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Table(name = "lookup_categories",
        schema = "admin",
        uniqueConstraints = {
                @UniqueConstraint(name = "lookup_categories_code_key",
                        columnNames = "code")
        })

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LookupCategory {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="code", length = 80, nullable = false, unique = true)
    private String code;

    @Column(name="label", length = 120, nullable = false)
    private String label;

    @Column(name="description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private OffsetDateTime created_at;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private OffsetDateTime updated_at;
}

