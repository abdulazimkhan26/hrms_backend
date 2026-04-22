package com.hrms.hrCore.repository;

import com.hrms.hrCore.entity.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PositionsRepository extends JpaRepository<Positions, UUID> {
    Positions findByPositionCode(String positionCode);
}
