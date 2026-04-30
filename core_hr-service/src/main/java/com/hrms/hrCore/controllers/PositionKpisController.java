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
import com.hrms.hrCore.dtos.requests.PositionKpisRequest;
import com.hrms.hrCore.service.PositionKpisService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/core/posKpis")
@RequiredArgsConstructor
public class PositionKpisController {
    private final PositionKpisService posKpiService;    

        @GetMapping("/allPosKpis")
    public ResponseEntity<?> allPos(){
        return posKpiService.allPosKpis();
    }

    @PostMapping("/createPosKpi")
    public ResponseEntity<?> createPos(@RequestBody PositionKpisRequest request){
        return posKpiService.createPosKpi(request);
    }

    @PutMapping("/updatePosKpi/{id}")
    public ResponseEntity<?> updatePos(@PathVariable UUID id, @RequestBody PositionKpisRequest request){
        return posKpiService.updatePosKpi(id,request);
    }

    @DeleteMapping("/deletePosKpi/{id}")
    public ResponseEntity<?> deletePos(@PathVariable UUID id){
        return posKpiService.deletePosKpi(id);
    }
    
}
