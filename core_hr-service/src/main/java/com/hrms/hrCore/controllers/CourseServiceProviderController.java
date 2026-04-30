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

import com.hrms.hrCore.dtos.requests.CourseServiceProvidersRequest;
import com.hrms.hrCore.service.CourseServiceProvidersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/core/CSP")
@RequiredArgsConstructor
public class CourseServiceProviderController {
    private final CourseServiceProvidersService CSP_service;

    @GetMapping("/allCsps")
    public ResponseEntity<?> allCsps(){ return CSP_service.allCsps(); }
    @PostMapping("/createCsp")
    public ResponseEntity<?> createCsp(@RequestBody CourseServiceProvidersRequest cspReq){ return CSP_service.createCsp(cspReq); }
    @PutMapping("/updateCsp/{id}")
    public ResponseEntity<?> updateCsp(@PathVariable UUID id, @RequestBody CourseServiceProvidersRequest cspReq){ return CSP_service.updateCsp(id, cspReq); }
    @DeleteMapping("/deleteCsp/{id}")
    public ResponseEntity<?> deleteCsp(@PathVariable UUID id){ return CSP_service.deleteCsp(id); }    
}
