package com.hrms.admin.controllers;

import com.hrms.admin.dto.LookUpCategoryRequest;
import com.hrms.admin.dto.LookUpCategoryResponse;
import com.hrms.admin.dto.LookUpValueRequest;
import com.hrms.admin.dto.LookUpValueResponse;
import com.hrms.admin.projection.LookupCategoryProjection;
import com.hrms.admin.service.LookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class LookupController {
    private LookupService lookupservice;

    public LookupController(LookupService lookupService){
        this.lookupservice = lookupService;
    }

    // LookupValues requests -------------------------------------------------
    @GetMapping("/all_categories")
    public ResponseEntity<List<LookupCategoryProjection>> fetchcat(){
        return ResponseEntity.status(200).body(lookupservice.allCategories());
    }

    @PostMapping("/create_category")
    public ResponseEntity<LookUpCategoryResponse> createCat(@RequestBody LookUpCategoryRequest request){
        return ResponseEntity.status(200).body(lookupservice.create_Category(request));
    }

    @DeleteMapping("/delete_category/{id}")
    public ResponseEntity<LookUpCategoryResponse> deleteCat(@PathVariable UUID id){
        return ResponseEntity.status(200).body(lookupservice.delete_category(id));
    }

    @PutMapping("/update_category/{id}")
    public ResponseEntity<LookUpCategoryResponse> updateCat(@PathVariable UUID id, @RequestBody LookUpCategoryRequest request){
        return ResponseEntity.status(200).body(lookupservice.update_category(id, request));
    }

    // LookupValues requests -------------------------------------------------
    // @GetMapping("/lookupvalue/{code}")
    // public LookUpValueResponse getLookupValueByCode(@PathVariable String code) {
    //     return lookupservice.getLookupValueByCode(code);
    // }

    @GetMapping("/all_values/{category_id}")
    public ResponseEntity<?> fetchval(@PathVariable UUID category_id) {
        return lookupservice.all_values(category_id);
    }

    @PostMapping("/create_value")
    public ResponseEntity<?> createVal(@RequestBody LookUpValueRequest request){
        return lookupservice.create_values(request);
    }

    @PutMapping("/update_value/{id}")
    public ResponseEntity<?> updateVal(@PathVariable UUID id, @RequestBody LookUpValueRequest request){
        return lookupservice.update_values(id, request);
    }

    @DeleteMapping("/delete_value/{id}")
    public ResponseEntity<?> deleteVal(@PathVariable UUID id){
        return lookupservice.delete_values(id);
    }

}
