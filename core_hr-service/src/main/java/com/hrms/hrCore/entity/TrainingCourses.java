package com.hrms.hrCore.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name="training_courses", schema="core")
public class TrainingCourses {

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false, updatable=false)
    private UUID id;
    
    @Column(name="course_code", nullable=false)
    private String courseCode;

    @Column(name="course_name", nullable=false)
    private String courseName;

    @Column(name="course_description", nullable=false)
    private String courseDescription;

    @Column(name="aqf_level_id", nullable=false)
    private UUID aqfLevelId;

    @Column(name="category_id", nullable=false)
    private UUID categoryId;

    @Column(name="provider_id", nullable=false)
    private UUID providerId;
    
    @Column(name="cost", nullable=false, precision=3, scale=2)
    private BigDecimal cost;

    @Column(name="is_active", nullable=false)
    private Boolean active;

    @Column(name="created_by", nullable=false)
    private UUID createdBy;

    @CreationTimestamp
    @Column(name="created_at", nullable=false)
    private UUID createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable=false)
    private UUID updatedAt;
}
