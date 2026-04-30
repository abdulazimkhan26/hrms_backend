package com.hrms.hrCore.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.ErrorHandling.BadRequestException;
import com.hrms.hrCore.ErrorHandling.ResourceNotFoundException;
import com.hrms.hrCore.dtos.requests.PositionKpisRequest;
import com.hrms.hrCore.entity.Kpis;
import com.hrms.hrCore.entity.PositionKpis;
import com.hrms.hrCore.entity.Positions;
import com.hrms.hrCore.repository.KpiRepository;
import com.hrms.hrCore.repository.PositionKpisRepository;
import com.hrms.hrCore.repository.PositionsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionKpisService {
    private final PositionKpisRepository posKpi_repo;
    private final KpiRepository kpi_repo;
    private final PositionsRepository pos_repo;

    public ResponseEntity<?> allPosKpis(){
        List<PositionKpis> posKpis = posKpi_repo.findAll();
        if(posKpis.isEmpty()){ throw new ResourceNotFoundException("No existing records for Positions Kpis."); }
        
        List<Map<String, Object>> PosKpis= posKpis.stream().map(pk -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", pk.getId());
            map.put("positionId", pk.getPositions().getId());
            map.put("positionName", pk.getPositions().getPositionName());
            map.put("kpiId", pk.getKpi().getId());
            map.put("kpiName", pk.getKpi().getKpiName());
            map.put("weighting", pk.getWeighting());
            map.put("createdAt", pk.getCreatedAt());

            return map;
        }).toList();

        return ResponseEntity.status(200).body(PosKpis);
    }

    public ResponseEntity<?> createPosKpi(PositionKpisRequest req) {
        if (req.getKpi() == null || req.getPositions() == null) {
            throw new IllegalArgumentException("kpiId and positionId must not be null");
        }

        Kpis kpi = kpi_repo.findById(req.getKpi())
                .orElseThrow(() -> new RuntimeException("KPI not found"));
        Positions pos = pos_repo.findById(req.getPositions())
                .orElseThrow(() -> new RuntimeException("Position not found"));
        Optional<PositionKpis> existing = posKpi_repo.findByPositionAndKpi(pos, kpi);

        if (existing.isPresent()) {
            throw new DataIntegrityViolationException(
                    "Relation for " + pos.getPositionName() + " & " + kpi.getKpiName() + "exists!"
            );
        }

        PositionKpis entity = PositionKpis.builder()
                .positions(pos)
                .kpi(kpi)
                .weighting(req.getWeighting())
                .build();

        posKpi_repo.save(entity);
        return ResponseEntity.ok("PositionKpis created successfully!");
    }

    public ResponseEntity<?> updatePosKpi(UUID id, PositionKpisRequest req) {
        if (req.getPositions() == null || req.getKpi() == null) {
            throw new BadRequestException("positionId and kpiId must not be null");
        }
        if (req.getWeighting().compareTo(BigDecimal.ZERO) < 0 ||
            req.getWeighting().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new BadRequestException("Weighting must be between 0 and 100");
        }

        PositionKpis existing = posKpi_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PositionKpi doesn't exist"));
        Positions pos = pos_repo.findById(req.getPositions())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
        Kpis kpi = kpi_repo.findById(req.getKpi())
                .orElseThrow(() -> new ResourceNotFoundException("KPI not found"));

        boolean isSameCombination =
                existing.getPositions().getId().equals(pos.getId()) &&
                existing.getKpi().getId().equals(kpi.getId());

        if (!isSameCombination) {

            posKpi_repo.findByPositionAndKpi(pos, kpi)
                    .ifPresent(p -> {
                        throw new DataIntegrityViolationException(
                                "Relation already exists for " +
                                pos.getPositionName() + " & " +
                                kpi.getKpiName()
                        );
                    });
        }

        boolean updated = false;
        if (!existing.getPositions().getId().equals(pos.getId())) {
            existing.setPositions(pos);
            updated = true;
        }
        if (!existing.getKpi().getId().equals(kpi.getId())) {
            existing.setKpi(kpi);
            updated = true;
        }
        if (existing.getWeighting().compareTo(req.getWeighting()) != 0) {
            existing.setWeighting(req.getWeighting());
            updated = true;
        }

        if (updated) {
            posKpi_repo.save(existing);
            return ResponseEntity.ok("PositionKpis updated successfully!");
        }
        return ResponseEntity.ok("No changes detected");
    }
    
    public ResponseEntity<?> deletePosKpi(UUID id){
       PositionKpis posKpis = posKpi_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("PositionKpi doesn't exist!"));      
       
       posKpi_repo.delete(posKpis);
       return ResponseEntity.status(200).body("PositionKpis deleted successfully!");
    }
}

