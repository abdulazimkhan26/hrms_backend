package com.hrms.hrCore.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.hrCore.ErrorHandling.ResourceNotFoundException;
import com.hrms.hrCore.dtos.requests.TrainingCoursesRequest;
import com.hrms.hrCore.entity.Positions;
import com.hrms.hrCore.entity.TrainingCourses;
import com.hrms.hrCore.repository.TrainingCoursesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingCoursesService {
    private final TrainingCoursesRepository tc_repo;

    public ResponseEntity<?> allTc(){
        List<TrainingCourses> tc = tc_repo.findAll();
        if(tc.isEmpty()){
            throw new ResourceNotFoundException("No records for Training courses found!");
        }

        return ResponseEntity.status(200).body(tc);
    }

    public ResponseEntity<?> createTc(TrainingCoursesRequest req){
        Optional<TrainingCourses> tc = tc_repo.findByCourseCode(req.getCourseCode());
        if(tc.isPresent()){
            throw new DataIntegrityViolationException(req.getCourseCode() + " already exists. Try another code.");
        }

        TrainingCourses TC = TrainingCourses.builder()
               .courseCode(req.getCourseCode())
               .courseName(req.getCourseName())
               .courseDescription(req.getCourseDescription())
               .aqfLevelId(req.getAqfLevelId())
               .categoryId(req.getCategoryId())
               .providerId(req.getProviderId())
               .cost(req.getCost())
               .active(true)
               .createdBy(req.getCreatedBy())
               .build();

        tc_repo.save(TC);
        return ResponseEntity.status(200).body("Course created successfully!");
    }    

    public ResponseEntity<?> updateTc(UUID id, TrainingCoursesRequest req){
        TrainingCourses tc = tc_repo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Training course doesn't exist!"));
              
        tc_repo.findByCourseCode(req.getCourseCode())
         .ifPresent(p -> {
                if (!p.getId().equals(id)) {
                    throw new DataIntegrityViolationException(req.getCourseCode() + " already exists! Please use another code.");
                }
            });

        boolean isUpdated = false;
        if (!Objects.equals(tc.getCourseCode(), req.getCourseCode())) {
            tc.setCourseCode(req.getCourseCode());
            isUpdated = true;
        }

        if (!Objects.equals(tc.getCourseName(), req.getCourseName())) {
            tc.setCourseName(req.getCourseName());
            isUpdated = true;
        }

        if (!Objects.equals(tc.getCourseDescription(), req.getCourseDescription())) {
            tc.setCourseDescription(req.getCourseDescription());
            isUpdated = true;
        }

        if (!Objects.equals(tc.getAqfLevelId(), req.getAqfLevelId())) {
            tc.setAqfLevelId(req.getAqfLevelId());
            isUpdated = true;
        }

        if (!Objects.equals(tc.getCategoryId(), req.getCategoryId())) {
            tc.setCategoryId(req.getCategoryId());
            isUpdated = true;
        }

        if (!Objects.equals(tc.getProviderId(), req.getProviderId())) {
            tc.setProviderId(req.getProviderId());
            isUpdated = true;
        }

        if (!Objects.equals(tc.getCost(), req.getCost())) {
            tc.setCost(req.getCost());
            isUpdated = true;
        }

        if (isUpdated){ 
          tc_repo.save(tc); 
          return ResponseEntity.ok("Position updated successfully!"); 
        } 
        else { return ResponseEntity.ok("No changes detected"); }
    }    

    public ResponseEntity<?> deleteTC(UUID id){
        TrainingCourses tc = tc_repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Course doesn't exist!"));

        tc_repo.delete(tc);
        return ResponseEntity.ok("Position deleted successfully!"); 

    }
}
