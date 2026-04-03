package com.hrms.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

@Entity
@Table(name = "users", schema = "core")
public class User {
    @Getter
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Generated(event = EventType.INSERT)
    private String id;

    @Getter
    @Setter
    @Column(name = "username", nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Getter
    @Setter
    @Column(name="roles", nullable = false)
    private String role;


}