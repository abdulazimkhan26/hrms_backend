package com.hrms.hrCore.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.ErrorHandling.BadRequestException;
import com.hrms.hrCore.ErrorHandling.ResourceNotFoundException;
import com.hrms.hrCore.dtos.requests.JobsRequest;
import com.hrms.hrCore.entity.Jobs;
import com.hrms.hrCore.repository.JobsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobsService {
    private final JobsRepository jobs_repo;

    public ResponseEntity<?> allJobs(){
        List<Jobs> job = jobs_repo.findAll();
        if(job.isEmpty()){
            throw new ResourceNotFoundException("No record for jobs!");
        }
        return ResponseEntity.status(200).body(job);
    }

    public ResponseEntity<?> createJobs(JobsRequest req){
        Optional<Jobs> job = jobs_repo.findByJobCode(req.getJobCode());
        if(!job.isEmpty()){ throw new DataIntegrityViolationException(req.getJobCode() + "already exists. Try another code.");}

        Jobs Job = Jobs.builder()
            .jobCode(req.getJobCode())
            .jobTitle(req.getJobTitle())
            .positionId(req.getPositionId())
            .reportsToJobId(req.getReportsToJobId())
            .salary(req.getSalary())
            .workTypeId(req.getWorkTypeId())
            .contractLength(req.getContractLength())
            .weeklyHours(req.getWeeklyHours())
            .startDate(req.getStartDate())
            .endDate(req.getEndDate())
            .stateId(req.getStateId())
            .locationId(req.getLocationId())
            .companyId(req.getCompanyId())
            .departmentId(req.getDepartmentId())
            .costCentreId(req.getCostCentreId())
            .status(req.getStatus())
            .approvedBy(req.getApprovedBy())
            .approved_at(req.getApproved_at())
            .declineReason(req.getDeclineReason())
            .active(true)
            .createdBy(req.getCreatedBy())
            .build();

        try{
            jobs_repo.save(Job);
            return ResponseEntity.status(200).body("Job created successfully!");
        }
        catch(Exception e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
    
    public ResponseEntity<?> updateJobs(UUID id, JobsRequest req){
        Jobs job = jobs_repo.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Job not found!"));

        jobs_repo.findByJobCode(req.getJobCode())
            .ifPresent(p -> {
                if (!p.getId().equals(id)) {
                    throw new DataIntegrityViolationException(req.getJobCode() + " already exists! Please use another code.");
                }
        });

        boolean isUpdated = false;

        if (req.getEndDate() != null && req.getStartDate() != null &&
            req.getEndDate().isBefore(req.getStartDate())) {
            throw new BadRequestException("End date cannot be before start date");
        }

        if (!Objects.equals(job.getJobCode(), req.getJobCode())) {
            job.setJobCode(req.getJobCode());
            isUpdated = true;
        }

        if (!Objects.equals(job.getJobTitle(), req.getJobTitle())) {
            job.setJobTitle(req.getJobTitle());
            isUpdated = true;
        }

        if (!Objects.equals(job.getPositionId(), req.getPositionId())) {
            job.setPositionId(req.getPositionId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getReportsToJobId(), req.getReportsToJobId())) {
            job.setReportsToJobId(req.getReportsToJobId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getSalary(), req.getSalary())) {
            job.setSalary(req.getSalary());
            isUpdated = true;
        }

        if (!Objects.equals(job.getWorkTypeId(), req.getWorkTypeId())) {
            job.setWorkTypeId(req.getWorkTypeId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getContractLength(), req.getContractLength())) {
            job.setContractLength(req.getContractLength());
            isUpdated = true;
        }

        if (!Objects.equals(job.getWeeklyHours(), req.getWeeklyHours())) {
            job.setWeeklyHours(req.getWeeklyHours());
            isUpdated = true;
        }

        if (!Objects.equals(job.getStartDate(), req.getStartDate())) {
            job.setStartDate(req.getStartDate());
            isUpdated = true;
        }

        if (!Objects.equals(job.getEndDate(), req.getEndDate())) {
            job.setEndDate(req.getEndDate());
            isUpdated = true;
        }

        if (!Objects.equals(job.getStateId(), req.getStateId())) {
            job.setStateId(req.getStateId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getLocationId(), req.getLocationId())) {
            job.setLocationId(req.getLocationId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getCompanyId(), req.getCompanyId())) {
            job.setCompanyId(req.getCompanyId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getDepartmentId(), req.getDepartmentId())) {
            job.setDepartmentId(req.getDepartmentId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getCostCentreId(), req.getCostCentreId())) {
            job.setCostCentreId(req.getCostCentreId());
            isUpdated = true;
        }

        if (!Objects.equals(job.getStatus(), req.getStatus())) {
            job.setStatus(req.getStatus());
            isUpdated = true;
        }

        if (!Objects.equals(job.getApprovedBy(), req.getApprovedBy())) {
            job.setApprovedBy(req.getApprovedBy());
            isUpdated = true;
        }

        if (!Objects.equals(job.getApproved_at(), req.getApproved_at())) {
            job.setApproved_at(req.getApproved_at());
            isUpdated = true;
        }

        if (!Objects.equals(job.getDeclineReason(), req.getDeclineReason())) {
            job.setDeclineReason(req.getDeclineReason());
            isUpdated = true;
        }

        if (!Objects.equals(job.getActive(), req.getActive())) {
            job.setActive(req.getActive());
            isUpdated = true;
        }

        if (isUpdated) {
            jobs_repo.save(job);
            return ResponseEntity.ok("Job updated successfully!");
        } else {
            return ResponseEntity.ok("No changes detected");
        }

    }
    
    public ResponseEntity<?> deleteJobs(UUID id){
        Jobs job = jobs_repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found!"));   
                    
        jobs_repo.delete(job);
        return ResponseEntity.ok("Job deleted successfully!");
    }
}
