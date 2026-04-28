package com.hrms.hrCore.service;

import com.hrms.hrCore.dtos.requests.PositionRequest;
import com.hrms.hrCore.entity.Positions;
import com.hrms.hrCore.repository.PositionsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public ResponseEntity<?> deletePositions(UUID id){
        Optional<Positions> position = positions_repo.findById(id);
        if(position.isEmpty()){
          return ResponseEntity.status(404).body("No existing positions!");
        }

        positions_repo.delete(position.get());
        return ResponseEntity.status(200).body("Position deleted successfully!");
    }


}
