package com.hrms.hrCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@Table(name = "qualifications", schema = "core")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Qualification {
    
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(name = "qualification_code", nullable = false, unique = true)
    private String qualificationCode;
    
    @Column(name = "qualification_name", nullable = false)
    private String qualificationName;
    
    @Column(name = "qualification_description", nullable = false)
    private String qualificationDescription;
    
    @Column(name = "aqf_level_id", nullable = false)
    private UUID aqfLevelId;
    
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean active = true;
    
    @Column (name = "created_by", nullable = false)
    private UUID createdBy;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;    
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
