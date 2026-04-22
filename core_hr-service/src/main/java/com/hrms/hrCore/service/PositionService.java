package com.hrms.hrCore.service;

import com.hrms.hrCore.client.AdminClient;
import com.hrms.hrCore.dtos.requests.PositionRequest;
import com.hrms.hrCore.dtos.responses.LookupValueResponse;
import com.hrms.hrCore.dtos.responses.PositionResponse;
import com.hrms.hrCore.entity.Positions;
import com.hrms.hrCore.repository.PositionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

// service/EmployeeService.java
@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionsRepository positionsRepository;
    private final AdminClient adminClient;  // ← Feign

    // CREATE — just save the UUID
    public PositionResponse createPositions(PositionRequest request) {
        LookupValueResponse position_level = adminClient.getLookupValueByCode(request.getPositionLevelCode());
        LookupValueResponse work_type = adminClient.getLookupValueByCode(request.getWorkTypeCode());

        System.out.println("Position Level ID: " + position_level.getId());
        System.out.println("Work Type ID: " + work_type.getId());

        Positions position = Positions.builder()
                .positionCode(request.getPositionCode())
                .positionName(request.getPositionName())
                .positionDescription(request.getPositionDescription())
                .positionLevelId(position_level.getId())
                .salaryMinimum(request.getSalaryMinimum())
                .salaryMaximum(request.getSalaryMaximum())
                .workTypeId(work_type.getId())
                .contractLength(request.getContractLength())
                .status("DRAFT")
                .weeklyHoursMin(request.getWeeklyHoursMin())
                .weeklyHoursMax(request.getWeeklyHoursMax())
                .reportsTo(positionsRepository.findByPositionCode(request.getReportsToId()))
                .created_by(request.getCreatedBy())
                .build();

        positionsRepository.save(position);
        return new PositionResponse("Positions created successfully!", true);
    }


}
