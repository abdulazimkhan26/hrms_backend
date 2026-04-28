package com.hrms.hrCore.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.dtos.requests.QualificationRequest;
import com.hrms.hrCore.entity.Qualification;
import com.hrms.hrCore.repository.QualificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QualificationService {
    private final QualificationRepository qualification_repo;

    public ResponseEntity<?> allQualifications(){
        List<Qualification> qual = qualification_repo.findAll();  
        if(qual.isEmpty()){
          return ResponseEntity.status(404).body("No records found for Qualifications");

        }      
        return ResponseEntity.status(200).body(qual);
    }        

    public ResponseEntity<?> createQualifications(QualificationRequest request){
        Optional<Qualification> qual = qualification_repo.findByQualificationCode(request.getQualificationCode());

        if(!qual.isEmpty()){
            return ResponseEntity.status(404).body("The Qualification Code already exists. Please change it.");
        }

        Qualification qualification = Qualification.builder()
                .qualificationCode(request.getQualificationCode())
                .qualificationName(request.getQualificationName())
                .qualificationDescription(request.getQualificationDescription())
                .aqfLevelId(request.getAqfLevelId())
                .createdBy(request.getCreatedBy())
                .build();
        
        qualification_repo.save(qualification);

        return ResponseEntity.status(200).body(request.getQualificationName() + "created successfully");       
    }

    public ResponseEntity<?> updateQualifications(UUID id, QualificationRequest request){
        Optional<Qualification> qual = qualification_repo.findById(id);
        if(qual.isEmpty()){
            return ResponseEntity.status(404).body("No existing qualification!");
        }

        Qualification qualification = qual.get();
        boolean isUpdated = false;

        if (!Objects.equals(qualification.getQualificationCode(), request.getQualificationCode())) {
            qualification.setQualificationCode(request.getQualificationCode());
            isUpdated = true;
        }

        if (!Objects.equals(qualification.getQualificationName(), request.getQualificationName())) {
            qualification.setQualificationName(request.getQualificationName());
            isUpdated = true;
        }

        if (!Objects.equals(qualification.getQualificationDescription(), request.getQualificationDescription())) {
            qualification.setQualificationDescription(request.getQualificationDescription());
            isUpdated = true;
        }

        if (!Objects.equals(qualification.getAqfLevelId(), request.getAqfLevelId())) {
            qualification.setAqfLevelId(request.getAqfLevelId());
            isUpdated = true;
        }

        if (isUpdated) {
            qualification_repo.save(qualification);
            return ResponseEntity.status(200).body("Updated sucessfully!");
        } else {
            return ResponseEntity.status(200).body("No changes detected");
        }
    }

    public ResponseEntity<?> deleteQualification(UUID id){
        Optional<Qualification> qual = qualification_repo.findById(id);
        Qualification qualification = qual.get();
        qualification_repo.delete(qualification);
        return ResponseEntity.status(200).body("Qualification deleted Successfully!");
    }

}
