package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "lookup_values",
        schema="admin",
        uniqueConstraints = {
                    @UniqueConstraint(name = "lookup_values_category_id_display_value_key",
                            columnNames = "category_id, display_value")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LookupValues {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private LookupCategories category;

    @Column(name = "display_value", length = 120, nullable = false)
    private String display_value;

    @Column(name = "code", length = 80, nullable = false, unique = true)
    private String code;

    @Column(name = "sort_order", nullable = false)
    private Integer sort_order;

    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @PrePersist
    public void prePersist() {
        if (sort_order == null) {
            sort_order = 0;
        }
    }
}


