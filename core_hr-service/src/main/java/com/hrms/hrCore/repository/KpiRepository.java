package com.hrms.hrCore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.hrCore.entity.Kpis;

@Repository
public interface KpiRepository extends JpaRepository<Kpis, UUID> {
    Optional<Kpis> findByKpiCode(String code);
}
