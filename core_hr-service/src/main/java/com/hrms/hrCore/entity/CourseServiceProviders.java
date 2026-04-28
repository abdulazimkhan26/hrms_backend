package com.hrms.hrCore.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@Builder
@DynamicUpdate
@Table(name="course_service_providers", schema="core")
public class CourseServiceProviders {

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false, updatable=false)
    private UUID id;
    
    @Column(name="provider_number", nullable=false)
    private String providerNumber;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="contact_person", nullable=false)
    private String contactPerson;

    @Column(name="telephone", nullable=false)
    private String telephone;

    @Column(name="address", nullable=false, columnDefinition="TEXT")
    private String address;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="is_active", nullable=false)
    private Boolean active;

    @Column(name="created_by", nullable=false)
    private UUID createdBy;

    @UpdateTimestamp
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
}
