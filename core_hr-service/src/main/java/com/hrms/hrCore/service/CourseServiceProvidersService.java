package com.hrms.hrCore.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.dtos.requests.CourseServiceProvidersRequest;
import com.hrms.hrCore.entity.CourseServiceProviders;
import com.hrms.hrCore.entity.Kpis;
import com.hrms.hrCore.repository.CourseServiceProvidersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceProvidersService {
    private final CourseServiceProvidersRepository csp_repo;

    public ResponseEntity<?> allCsps(){
        List<CourseServiceProviders> csp = csp_repo.findAll();
        if(csp.isEmpty()){return ResponseEntity.status(200).body("No existing Course Service Providers!");}
        return ResponseEntity.status(200).body(csp);
    }

    public ResponseEntity<?> createCsp(CourseServiceProvidersRequest req){
        Optional<CourseServiceProviders> csp = csp_repo.findByname(req.getName());
        Optional<CourseServiceProviders> csp1 = csp_repo.findByproviderNumber(req.getProviderNumber());
        if(csp.isPresent()){
           return ResponseEntity.status(200).body(req.getName() + "already exists!");
        }
        if(csp1.isPresent()){
           return ResponseEntity.status(200).body(req.getProviderNumber() + "already exists!");
        }
        CourseServiceProviders CSP = CourseServiceProviders.builder()
                                     .providerNumber(req.getProviderNumber())
                                     .name(req.getName())
                                     .contactPerson(req.getContactPerson())
                                     .address(req.getAddress())
                                     .email(req.getEmail())
                                     .createdBy(req.getCreatedBy())
                                     .active(true)
                                     .telephone(req.getTelephone())
                                     .build();
        csp_repo.save(CSP);

        return ResponseEntity.status(200).body("Course Service Provider created successfully!");
    }

    public ResponseEntity<?> updateCsp(UUID id, CourseServiceProvidersRequest req){
        Optional<CourseServiceProviders> csp = csp_repo.findById(id);
        if(csp.isEmpty()){
        return ResponseEntity.status(200).body("No selected CSP found!");           
        }

        CourseServiceProviders CSP = csp.get();
        Boolean isUpdated = false;
        
        if (!Objects.equals(CSP.getProviderNumber(), req.getProviderNumber())) {
            CSP.setProviderNumber(req.getProviderNumber());
            isUpdated = true;
        }

        if (!Objects.equals(CSP.getName(), req.getName())) {
            CSP.setName(CSP.getName());
            isUpdated = true;
        }

        if (!Objects.equals(CSP.getContactPerson(), req.getContactPerson())) {
            CSP.setContactPerson(req.getContactPerson());
            isUpdated = true;
        }

        if (!Objects.equals(CSP.getEmail(), req.getEmail())) {
            CSP.setEmail(req.getEmail());
            isUpdated = true;
        }

        if (!Objects.equals(CSP.getTelephone(), req.getTelephone())) {
            CSP.setTelephone(req.getTelephone());;
            isUpdated = true;
        }

        if (!Objects.equals(CSP.getAddress(), req.getAddress())) {
            CSP.setAddress(req.getAddress());
            isUpdated = true;
        }

        if (!Objects.equals(CSP.getActive(), req.getActive())) {
            CSP.setActive(req.getActive());
            isUpdated = true;
        }

        if (isUpdated) {
            csp_repo.save(CSP);
            return ResponseEntity.status(200).body("CSP Updated sucessfully!");
        } else {
            return ResponseEntity.status(200).body("No changes detected");
        }
    }

    public ResponseEntity<?> deleteCsp(UUID id){
        Optional<CourseServiceProviders> csp = csp_repo.findById(id);
        if(csp.isEmpty()){
        return ResponseEntity.status(200).body("Invalid CSP id!");           
        }        

        csp_repo.delete(csp.get());

        return ResponseEntity.status(200).body("CSP deleted sucessfully.");

    }
    
}
