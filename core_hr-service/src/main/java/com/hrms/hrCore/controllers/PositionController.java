package com.hrms.hrCore.controllers;

import com.hrms.hrCore.dtos.requests.PositionRequest;
import com.hrms.hrCore.service.PositionService;

import lombok.RequiredArgsConstructor;

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

@RestController
@RequestMapping("/core/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @GetMapping("/allPositions")
    public ResponseEntity<?> allPos(){
        return ResponseEntity.status(200).body(positionService.allPositions());
    }

    @PostMapping("/createPosition")
    public ResponseEntity<?> createPos(@RequestBody PositionRequest request){
        return ResponseEntity.status(200).body(positionService.createPositions(request));
    }

    @PutMapping("/updatePosition/{id}")
    public ResponseEntity<?> updatePos(@PathVariable UUID id, @RequestBody PositionRequest request){
        return ResponseEntity.status(200).body(positionService.updatePositions(id,request));
    }

    @DeleteMapping("/deletePosition/{id}")
    public ResponseEntity<?> deletePos(@PathVariable UUID id){
        return ResponseEntity.status(200).body(positionService.deletePositions(id));
    }
}
