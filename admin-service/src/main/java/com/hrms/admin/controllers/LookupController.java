package com.hrms.admin.controllers;

import com.hrms.admin.dto.LookUpCategoryRequest;
import com.hrms.admin.dto.LookUpCategoryResponse;
import com.hrms.admin.dto.LookUpValueRequest;
import com.hrms.admin.dto.LookUpValueResponse;
import com.hrms.admin.service.LookupService;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LookupController {
    private LookupService lookupservice;

    public LookupController(LookupService lookupService){
        this.lookupservice = lookupService;
    }

    // LookupValues requests -------------------------------------------------
    @GetMapping("/all_categories")
    public ResponseEntity<List<String>> fetchcat(){
        return ResponseEntity.status(200).body(lookupservice.allCategories());
    }

    @PostMapping("/create_category")
    public ResponseEntity<LookUpCategoryResponse> createCat(@RequestBody LookUpCategoryRequest request){
        return ResponseEntity.status(200).body(lookupservice.create_Category(request));
    }

    @PostMapping("/delete_category/{code}")
    public ResponseEntity<LookUpCategoryResponse> deleteCat(@PathVariable String code){
        return ResponseEntity.status(200).body(lookupservice.delete_category(code));
    }

    @PostMapping("/update_category/{code}")
    public ResponseEntity<LookUpCategoryResponse> updateCat(@PathVariable String code, @RequestBody LookUpCategoryRequest request){
        return ResponseEntity.status(200).body(lookupservice.update_category(code, request));
    }

    // LookupValues requests -------------------------------------------------
    @GetMapping("/all_values/{category_code}")
    public ResponseEntity<List<String>> fetchval(@PathVariable String category_code){
        return ResponseEntity.status(200).body(lookupservice.all_values(category_code));
    }

    @PostMapping("/create_value")
    public ResponseEntity<LookUpValueResponse> createVal(@RequestBody LookUpValueRequest request){
        return ResponseEntity.status(200).body(lookupservice.create_values(request));
    }

    @PostMapping("/update_value/{code}")
    public ResponseEntity<LookUpValueResponse> updateVal(@PathVariable String code, @RequestBody LookUpValueRequest request){
        return ResponseEntity.status(200).body(lookupservice.update_values(code, request));
    }

    @PostMapping("/delete_value/{code}")
    public ResponseEntity<LookUpValueResponse> deleteVal(@PathVariable String code){
        return ResponseEntity.status(200).body(lookupservice.delete_values(code));
    }

}
