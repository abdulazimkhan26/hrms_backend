package com.hrms.hrCore.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.hrCore.dtos.requests.PositionQualificationsRequest;
import com.hrms.hrCore.service.PositionQualificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/core/posQual")
@RequiredArgsConstructor
public class PositionQualificationsController {
   private final PositionQualificationService posQual_service;    
    
   @GetMapping("/allposQuals")
    public ResponseEntity<?> all_Qualifications() {
        return posQual_service.allposQuals();
    }    
    
    @PostMapping("/createQualPos")
    public ResponseEntity<?> create_qualification(@RequestBody PositionQualificationsRequest request) {        
        return posQual_service.createQualPos(request);
    }

    @PutMapping("updateQualPos/{id}")
    public ResponseEntity<?> updateKpi(@PathVariable UUID id, @RequestBody PositionQualificationsRequest request) {        
        return posQual_service.updateQualPos(id, request);
    }

    @DeleteMapping("/deleteQualPos/{id}")
    public ResponseEntity<?> deleteKpi(@PathVariable UUID id){
        return posQual_service.deleteQualPos(id);
    }  
    
}
