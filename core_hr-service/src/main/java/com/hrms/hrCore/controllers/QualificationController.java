package com.hrms.hrCore.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.hrms.hrCore.dtos.requests.QualificationRequest;
import com.hrms.hrCore.service.QualificationService;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/core/qualifications")
@RequiredArgsConstructor
public class QualificationController {
    private final QualificationService qualificationService;

    @GetMapping("/allQualifications")
    public ResponseEntity<?> all_Qualifications() {
        return qualificationService.allQualifications();
    }    
    
    @PostMapping("/createQualification")
    public ResponseEntity<?> create_qualification(@RequestBody QualificationRequest request) {        
        return qualificationService.createQualifications(request);
    }

    @PutMapping("updateQualification/{id}")
    public ResponseEntity<?> updateQualification(@PathVariable UUID id, @RequestBody QualificationRequest request) {
        
        return ResponseEntity.status(200).body(qualificationService.updateQualifications(id, request));
    }

    @DeleteMapping("/deleteQualification/{id}")
    public ResponseEntity<?> deleteQualification(@PathVariable UUID id){
        return ResponseEntity.status(200).body(qualificationService.deleteQualification(id));
    }     
    
    
}
