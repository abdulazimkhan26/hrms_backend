package com.hrms.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "admin")
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private UUID id;

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
    @Column(name="password_reset_token")
    private String resetToken;

    @Getter
    @Setter
    @Column(name="password_reset_expires")
    private LocalDateTime resetExpires;

    @Getter
    @Setter
    @Column(name="last_login")
    private LocalDateTime lastLogin;

    @Getter
    @Setter
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @Column(name="roles", nullable = false)
    private UUID role;
}