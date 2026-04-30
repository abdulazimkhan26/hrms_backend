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

import com.hrms.hrCore.dtos.requests.KpiRequest;
import com.hrms.hrCore.service.KpiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/core/kpis")
@RequiredArgsConstructor
public class KpiController {
    private final KpiService kpiService;
    
   @GetMapping("/allKpis")
    public ResponseEntity<?> all_Qualifications() {
        return kpiService.allKpis();
    }    
    
    @PostMapping("/createKpi")
    public ResponseEntity<?> create_qualification(@RequestBody KpiRequest request) {        
        return kpiService.createKpi(request);
    }

    @PutMapping("updateKpi/{id}")
    public ResponseEntity<?> updateKpi(@PathVariable UUID id, @RequestBody KpiRequest request) {        
        return kpiService.updateKpi(id, request);
    }

    @DeleteMapping("/deleteKpi/{id}")
    public ResponseEntity<?> deleteKpi(@PathVariable UUID id){
        return kpiService.deleteKpis(id);
    }  
    
}
