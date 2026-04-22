package com.hrms.hrCore.controllers;

import com.hrms.hrCore.dtos.requests.PositionRequest;
import com.hrms.hrCore.dtos.responses.PositionResponse;
import com.hrms.hrCore.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr_core")
public class PositionController {
    private PositionService positionService;

    public PositionController(PositionService positionService){
        this.positionService = positionService;
    }

    @PostMapping("/create_position")
    public ResponseEntity<PositionResponse> createPos(@RequestBody PositionRequest request){
        return ResponseEntity.status(200).body(positionService.createPositions(request));
    }
}
