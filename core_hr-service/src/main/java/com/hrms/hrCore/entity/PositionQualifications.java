package com.hrms.hrCore.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="position_qualifications", schema="core")
public class PositionQualifications {

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false, updatable=false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="position_id", nullable=false, foreignKey= @ForeignKey(name="position_qualifications_position_id_fkey"))
    private UUID positionId;

    @Column(name="qualification_id", nullable=false)
    private UUID qualificationId;
    
    @Column(name="requirement_type", nullable=false)
    private String requirementType;

    @CreationTimestamp
    @Column(name="created_at", nullable=false)
    private OffsetDateTime createdAt;
}
