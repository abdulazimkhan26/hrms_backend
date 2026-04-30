package com.hrms.hrCore.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.ErrorHandling.ResourceNotFoundException;
import com.hrms.hrCore.dtos.requests.PositionQualificationsRequest;
import com.hrms.hrCore.entity.Kpis;
import com.hrms.hrCore.entity.PositionKpis;
import com.hrms.hrCore.entity.PositionQualifications;
import com.hrms.hrCore.entity.Positions;
import com.hrms.hrCore.entity.Qualification;
import com.hrms.hrCore.repository.PositionQualificationsRepository;
import com.hrms.hrCore.repository.PositionsRepository;
import com.hrms.hrCore.repository.QualificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionQualificationService {
    private final PositionQualificationsRepository posQual_repo;
    private final PositionsRepository pos_repo;
    private final QualificationRepository qual_repo;

    public ResponseEntity<?> allposQuals(){
        List<PositionQualifications> posQual = posQual_repo.findAll();
        if(posQual.isEmpty()){
            throw new ResourceNotFoundException("No existing records for Position Qualifications!");
        }
        return ResponseEntity.status(200).body(posQual);
    }

    public ResponseEntity<?> createQualPos(PositionQualificationsRequest req){
        if (req.getPositionId() == null || req.getQualificationId() == null) {
            throw new IllegalArgumentException("QualificationId and PositionId must not be null");
        }
        Optional<Positions> pos  = pos_repo.findById(req.getPositionId());
        Optional<Qualification> qual  = qual_repo.findById(req.getQualificationId());
        Optional<PositionQualifications> existing = posQual_repo.findByPositionAndQualifications(req.getPositionId(), req.getQualificationId());

        if (existing.isPresent()) {
            throw new DataIntegrityViolationException(
                    "Relation for " + pos.get().getPositionName() + " & " + qual.get().getQualificationName() + "exists!"
            );
        }

        PositionQualifications posQual = PositionQualifications.builder()
                 .positionId(req.getPositionId())
                 .qualificationId(req.getQualificationId())
                 .requirementType(req.getRequirementType())
                 .build();
        
        try{
            posQual_repo.save(posQual);
            return ResponseEntity.status(200).body("Position Qualification created successfully!");
        }
        catch(Exception e){
           throw new DataIntegrityViolationException(e.getMessage());
        }                 
    }

    public ResponseEntity<?> updateQualPos(UUID id, PositionQualificationsRequest req){
        if (req.getPositionId() == null || req.getQualificationId() == null) {
            throw new IllegalArgumentException("QualificationId and PositionId must not be null");
        }

        PositionQualifications existing = posQual_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PositionQualification doesn't exist"));
        Positions pos = pos_repo.findById(req.getPositionId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
        Qualification qual = qual_repo.findById(req.getQualificationId())
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not found"));

        Boolean isSameCombination =
                existing.getPositionId().equals(pos.getId()) &&
                existing.getQualificationId().equals(qual.getId());
        System.out.println(isSameCombination);

        if (!isSameCombination) {
            posQual_repo.findByPositionAndQualifications(req.getPositionId(), req.getQualificationId())
                    .ifPresent(p -> {
                        throw new DataIntegrityViolationException(
                                "Relation already exists for " +
                                pos.getPositionName() + " & " +
                                qual.getQualificationName()
                        );
                    });
        }
        
        boolean updated = false;
        if (!existing.getPositionId().equals(req.getPositionId())) {
            existing.setPositionId(req.getPositionId());
            updated = true;
        }
        if (!existing.getQualificationId().equals(req.getQualificationId())) {
            existing.setQualificationId(req.getQualificationId());
            updated = true;
        }
        if (!existing.getRequirementType().equals(req.getRequirementType())) {
            existing.setRequirementType(req.getRequirementType());
            updated = true;
        }
 
        if (updated==true) {
            try{
            posQual_repo.save(existing);
            return ResponseEntity.ok("PositionQualificationss updated successfully!");
            }
            catch(Exception e){
              throw new DataIntegrityViolationException(e.getMessage());               
            }
        }
        return ResponseEntity.ok("No changes detected");
    }

    public ResponseEntity<?> deleteQualPos(UUID id){
        PositionQualifications existing = posQual_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PositionQualification doesn't exist"));
                
        try{
            posQual_repo.delete(existing);
            return ResponseEntity.ok("PositionQualificationss deleted successfully!");
        }        
        catch(Exception e){
           throw new DataIntegrityViolationException(e.getMessage());
        }
    }


}
