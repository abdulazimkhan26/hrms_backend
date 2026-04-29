package com.hrms.hrCore.service;

import com.hrms.hrCore.ErrorHandling.BadRequestException;
import com.hrms.hrCore.ErrorHandling.ResourceNotFoundException;
import com.hrms.hrCore.dtos.requests.PositionRequest;
import com.hrms.hrCore.entity.Positions;
import com.hrms.hrCore.repository.PositionsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionsRepository positions_repo;

    public ResponseEntity<?> allPositions(){
        List<Positions> position = positions_repo.findAll();
        if(position.isEmpty()){
          return ResponseEntity.status(404).body("No existing positions!");
        }

        return ResponseEntity.status(200).body(position);
    }

    public ResponseEntity<?> createPositions(PositionRequest request) {
        Optional<Positions> pos = positions_repo.findByPositionCode(request.getPositionCode());
        if(pos.isPresent()){
            throw new DataIntegrityViolationException(request.getPositionCode() + " already exists! Please use another code.");
        }
        Positions position = Positions.builder()
                .positionCode(request.getPositionCode())
                .positionName(request.getPositionName())
                .positionDescription(request.getPositionDescription())
                .positionLevelId(request.getPositionLevelId())
                .salaryMinimum(request.getSalaryMinimum())
                .salaryMaximum(request.getSalaryMaximum())
                .workTypeId(request.getWorkTypeId())
                .contractLength(request.getContractLength())
                .status("DRAFT")
                .active(true)
                .weeklyHoursMin(request.getWeeklyHoursMin())
                .weeklyHoursMax(request.getWeeklyHoursMax())
                .reportsTo(request.getReportsToId())
                .createdBy(request.getCreatedBy())
                .build();

        positions_repo.save(position);
        return ResponseEntity.status(200).body("Positions created successfully!");
    }

    public ResponseEntity<?> updatePositions(UUID id, PositionRequest req){
        Positions position = positions_repo.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Position doesn't exist!"));
              
        positions_repo.findByPositionCode(req.getPositionCode())
            .ifPresent(p -> {
                if (!p.getId().equals(id)) {
                    throw new DataIntegrityViolationException(req.getPositionCode() + " already exists! Please use another code.");
                }
            });

        if (req.getSalaryMaximum().compareTo(req.getSalaryMinimum()) < 0) {
            throw new BadRequestException("Maximum salary cannot be less than minimum salary");
        }

        if (req.getWeeklyHoursMax().compareTo(req.getWeeklyHoursMin()) < 0) {
            throw new BadRequestException("Max working hours cannot be less than minimum.");
        }

        boolean isUpdated = false;
        if (!Objects.equals(position.getPositionCode(), req.getPositionCode())) {
            position.setPositionCode(req.getPositionCode());
            isUpdated = true;
        }

        if (!Objects.equals(position.getPositionName(), req.getPositionName())) {
            position.setPositionName(req.getPositionName());
            isUpdated = true;
        }

        if (!Objects.equals(position.getPositionDescription(), req.getPositionDescription())) {
            position.setPositionDescription(req.getPositionDescription());
            isUpdated = true;
        }

        if (!Objects.equals(position.getPositionLevelId(), req.getPositionLevelId())) {
            position.setPositionLevelId(req.getPositionLevelId());
            isUpdated = true;
        }

        if (!Objects.equals(position.getSalaryMinimum(), req.getSalaryMinimum())) {
            position.setSalaryMinimum(req.getSalaryMinimum());
            isUpdated = true;
        }

        if (!Objects.equals(position.getSalaryMaximum(), req.getSalaryMaximum())) {
            position.setSalaryMaximum(req.getSalaryMaximum());
            isUpdated = true;
        }

        if (!Objects.equals(position.getWorkTypeId(), req.getWorkTypeId())) {
            position.setWorkTypeId(req.getWorkTypeId());
            isUpdated = true;
        }

        if (!Objects.equals(position.getContractLength(), req.getContractLength())) {
            position.setContractLength(req.getContractLength());
            isUpdated = true;
        }

        if (!Objects.equals(position.getWeeklyHoursMin(), req.getWeeklyHoursMin())) {
            position.setWeeklyHoursMin(req.getWeeklyHoursMin());
            isUpdated = true;
        }

        if (!Objects.equals(position.getWeeklyHoursMax(), req.getWeeklyHoursMax())) {
            position.setWeeklyHoursMax(req.getWeeklyHoursMax());
            isUpdated = true;
        }

        if (!Objects.equals(position.getReportsTo(), req.getReportsToId())) {
            position.setReportsTo(req.getReportsToId());
            isUpdated = true;
        }

        if (!Objects.equals(position.getStatus(), req.getStatus())) {
            position.setStatus(req.getStatus());
            isUpdated = true;
        }

        if (!Objects.equals(position.getActive(), req.getActive())) {
            position.setActive(req.getActive());
            isUpdated = true;
        }

        if (isUpdated){ 
          positions_repo.save(position); 
          return ResponseEntity.ok("Position updated successfully!"); 
        } 
        else { return ResponseEntity.ok("No changes detected"); }
    }

    public ResponseEntity<?> deletePositions(UUID id){
        Optional<Positions> position = positions_repo.findById(id);
        if(position.isEmpty()){
          return ResponseEntity.status(404).body("No existing positions!");
        }

        positions_repo.delete(position.get());
        return ResponseEntity.status(200).body("Position deleted successfully!");
    }


}
