package com.hrms.hrCore.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.hrCore.dtos.requests.JobsRequest;
import com.hrms.hrCore.service.JobsService;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/core/jobs")
@RequiredArgsConstructor
public class JobsController {
    private final JobsService jobs_service;

    @GetMapping("/allJobs")
    public ResponseEntity<?> allJobs() {
        return jobs_service.allJobs();
    }

    @PostMapping("/createJob")
    public ResponseEntity<?> createjob(@RequestBody JobsRequest req) {
        return jobs_service.createJobs(req);        
    }

    @PutMapping("/updateJob/{id}")
    public ResponseEntity<?> updateJob(@PathVariable UUID id, @RequestBody JobsRequest req) {
        return jobs_service.updateJobs(id, req);
    }
    
    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable UUID id) {
        return jobs_service.deleteJobs(id);
    }
    
}
