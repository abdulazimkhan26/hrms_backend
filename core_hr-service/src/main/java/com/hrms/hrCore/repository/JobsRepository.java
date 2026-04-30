package com.hrms.hrCore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.hrCore.entity.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, UUID> {
    Optional<Jobs> findByJobCode(String code);
}
