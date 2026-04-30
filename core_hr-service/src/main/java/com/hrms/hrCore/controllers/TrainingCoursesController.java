package com.hrms.hrCore.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.hrCore.dtos.requests.TrainingCoursesRequest;
import com.hrms.hrCore.service.TrainingCoursesService;

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
@RequestMapping("/core/training_courses")
@RequiredArgsConstructor
public class TrainingCoursesController {
    private final TrainingCoursesService tsc;

    @GetMapping("/allTCs")
    public ResponseEntity<?> allTc() {
        return tsc.allTc();
    }
    @PostMapping("/createTc")
    public ResponseEntity<?> createTc(@RequestBody TrainingCoursesRequest tc) {
        return tsc.createTc(tc);
    }

    @PutMapping("/updateTc/{id}")
    public ResponseEntity<?> updateTc(@PathVariable UUID id, @RequestBody TrainingCoursesRequest tc) {
        return tsc.updateTc(id, tc);
    }

    @DeleteMapping("/deleteTc/{id}")
    public ResponseEntity<?> deleteTC(@PathVariable UUID id) {
        return tsc.deleteTC(id);

    }   
    
}
