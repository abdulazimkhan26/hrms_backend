package com.hrms.hrCore.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.hrCore.entity.Kpis;
import com.hrms.hrCore.entity.PositionKpis;
import com.hrms.hrCore.entity.Positions;

@Repository
public interface PositionKpisRepository extends JpaRepository<PositionKpis, UUID> {
    @Query("SELECT pk FROM PositionKpis pk WHERE pk.positions = :pos AND pk.kpi = :kpi")
    Optional<PositionKpis> findByPositionAndKpi(@Param("pos") Positions pos, @Param("kpi") Kpis kpi);
}
