package com.hrms.admin.repository;

import com.hrms.admin.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findByName(String name);
    boolean existsByName(String name);

    @Query("SELECT rl.name FROM Roles rl")
    List<String> findAllNames();
}
