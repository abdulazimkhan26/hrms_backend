package com.hrms.hrCore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hrms.hrCore.entity.PositionQualifications;

@Repository
public interface PositionQualificationsRepository extends JpaRepository<PositionQualifications, UUID>{
    @Query("SELECT pk FROM PositionQualifications pk WHERE pk.positionId = :pos AND pk.qualificationId = :qual")
    Optional<PositionQualifications> findByPositionAndQualifications(@Param("pos") UUID pos, @Param("qual") UUID qual);          
}
