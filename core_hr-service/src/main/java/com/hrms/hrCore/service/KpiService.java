package com.hrms.hrCore.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.ErrorHandling.ResourceNotFoundException;
import com.hrms.hrCore.dtos.requests.KpiRequest;
import com.hrms.hrCore.entity.Kpis;
import com.hrms.hrCore.repository.KpiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KpiService {
    private final KpiRepository kpi_repo;

    public ResponseEntity<?> allKpis(){
        List<Kpis> kpis = kpi_repo.findAll();
        if(kpis.isEmpty()){
           throw new ResourceNotFoundException("No KPI records are currently available.");
        }
           return ResponseEntity.status(200).body(kpis);
    }

    public ResponseEntity<?> createKpi(KpiRequest req){
        Optional<Kpis> kpis = kpi_repo.findByKpiCode(req.getKpiCode());
        if(kpis.isPresent()){
           throw new DataIntegrityViolationException(req.getKpiCode() + " already exists. Please use a unique KPI code.");
        }
        
        Kpis kpi = Kpis.builder()
        .kpiCode(req.getKpiCode())
        .kpiName(req.getKpiName())
        .kpiDescription(req.getKpiDescription())
        .active(true)
        .createdBy(req.getCreatedBy())
        .build();

        kpi_repo.save(kpi);
        return ResponseEntity.status(200).body("Kpi created Successfully.");
    }

    public ResponseEntity<?> updateKpi(UUID id, KpiRequest req){
        Optional<Kpis> kpi = kpi_repo.findById(id);
        if(kpi.isEmpty()){
           throw new ResourceNotFoundException("Kpi doesn't exists!");
        }

        Kpis kpis = kpi.get();
        Boolean isUpdated = false;
        
        if (!Objects.equals(kpis.getKpiCode(), req.getKpiCode())) {
            kpis.setKpiCode(req.getKpiCode());
            isUpdated = true;
        }

        if (!Objects.equals(kpis.getKpiName(), req.getKpiName())) {
            kpis.setKpiName(req.getKpiName());
            isUpdated = true;
        }

        if (!Objects.equals(kpis.getKpiDescription(), req.getKpiDescription())) {
            kpis.setKpiDescription(req.getKpiDescription());
            isUpdated = true;
        }

        if (isUpdated) {
            kpi_repo.save(kpis);
            return ResponseEntity.status(200).body("Kpi Updated sucessfully!");
        } else {
            return ResponseEntity.status(200).body("No changes detected");
        }
    }

    public ResponseEntity<?> deleteKpis(UUID id){
        Optional<Kpis> kpi = kpi_repo.findById(id);
        if(kpi.isEmpty()){
           throw new ResourceNotFoundException("Kpi doesn't exists!");
        }
        kpi_repo.delete(kpi.get());

       return ResponseEntity.status(200).body("Kpi Deleted sucessfully!");
    }
}
