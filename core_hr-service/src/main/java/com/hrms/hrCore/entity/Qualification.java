package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "qualifications", schema = "core")
public class Qualification {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "qualification_code", nullable = false, unique = true)
    private String qualification_code;

    @Getter
    @Setter
    @Column(name = "qualification_name", nullable = false)
    private String qualification_name;

    @Getter
    @Setter
    @Lob
    @Column(name = "qualification_description", nullable = false)
    private String qualification_description;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aqf_level_id", nullable = false, foreignKey = @ForeignKey(name = "qualifications_aqf_level_id_fkey"))
    private LookupValues lkv;

    @Getter
    @Setter
    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "created_by", nullable = false, foreignKey = @ForeignKey(name = "qualifications_created_by_fkey"))
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
