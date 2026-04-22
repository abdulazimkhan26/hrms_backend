package com.hrms.admin.service;

import com.hrms.admin.dto.LookUpCategoryRequest;
import com.hrms.admin.dto.LookUpCategoryResponse;
import com.hrms.admin.dto.LookUpValueRequest;
import com.hrms.admin.dto.LookUpValueResponse;
import com.hrms.admin.entity.LookupCategories;
import com.hrms.admin.entity.LookupValues;
import com.hrms.admin.projection.LookupCategoryProjection;
import com.hrms.admin.projection.LookupValuesProjection;
import com.hrms.admin.repository.LookupCategoriesRepository;
import com.hrms.admin.repository.LookupValuesRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LookupService {
    private LookupCategoriesRepository lookupcat_repo;
    private LookupValuesRepository lookupval_repo;

    public LookupService(LookupCategoriesRepository lookupcat_repo, LookupValuesRepository lookupval_repo){
        this.lookupcat_repo = lookupcat_repo;
        this.lookupval_repo = lookupval_repo;
    }

// ---LookUpCategories---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public List<LookupCategoryProjection> allCategories(){
        return lookupcat_repo.findAllCodes();
    }

    public LookUpCategoryResponse create_Category(LookUpCategoryRequest request){
        if(lookupcat_repo.existsByCode(request.getCode())){
            return new LookUpCategoryResponse("Category already exists!", request.getCode(), request.getLabel(), OffsetDateTime.now(), false);
        }

        LookupCategories category = new LookupCategories();
        category.setCode(request.getCode());
        category.setLabel(request.getLabel());
        category.setDescription(request.getDescription());

        lookupcat_repo.save(category);
        return new LookUpCategoryResponse("Category Created Successfully!", request.getCode(), request.getLabel(), OffsetDateTime.now(), true);
    }

    public LookUpCategoryResponse delete_category(UUID id){
        LookupCategories category = lookupcat_repo.findById(id).orElseThrow(()-> new RuntimeException("id not found!"));
        lookupcat_repo.delete(category);

        return new LookUpCategoryResponse(id + "deleted successfully", "", "", OffsetDateTime.now(), true);
    }

    public LookUpCategoryResponse update_category(UUID id, LookUpCategoryRequest request){
        LookupCategories category = lookupcat_repo.findById(request.getId())
                .orElseThrow(()-> new RuntimeException(""));

        category.setCode(request.getCode());
        category.setLabel(request.getLabel());
        category.setDescription(request.getDescription());

        lookupcat_repo.save(category);

        return new LookUpCategoryResponse(request.getId() + " successfully updated to " + request.getCode(), request.getCode(), request.getLabel(), OffsetDateTime.now(), true);
    }

// ---LookUpValues-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // public LookUpValueResponse getLookupValueByCode(String code) {
    //     LookupValues lv = lookupval_repo.findByCode(code)
    //             .orElseThrow(() -> new RuntimeException("Lookup value not found"));

    //     return new LookUpValueResponse(lv.getId().toString(),OffsetDateTime.now(),true, lv.getId());
    // }

    public ResponseEntity<?> all_values(UUID category_id) {    
        if (!lookupval_repo.existsByCategoryId(category_id)) {
            return ResponseEntity
                .status(400)
                .body(new LookUpValueResponse("No existing values found for the selected category.", null, false, category_id));
        }

        List<LookupValuesProjection> values = lookupval_repo.findByCategoryId(category_id);
        return ResponseEntity.status(200).body(values);
    }

    public ResponseEntity<?> create_values(LookUpValueRequest request){
        if(lookupval_repo.existsByCode(request.getCode())){
            return ResponseEntity.status(400).body(new LookUpValueResponse("The Value Code already exists!", OffsetDateTime.now(), false, null));
        }
        LookupCategories category = lookupcat_repo
                .findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        LookupValues values = new LookupValues();
        values.setDisplayValue(request.getDisplayValue());
        values.setCode(request.getCode());
        values.setCategoryId(request.getCategoryId());
        values.setActive(request.getActive());

        LookupValues saved = lookupval_repo.save(values);
        return ResponseEntity.status(200).body(new LookUpValueResponse(request.getCode() + " value successsfully added.", OffsetDateTime.now(), true, values.getId()));
    }

    public ResponseEntity<?> delete_values(UUID id){
        Optional<LookupValues> value = lookupval_repo.findById(id);
        if(value.isEmpty()) {
          return ResponseEntity.status(400).body(new LookUpValueResponse("No existing value found!", OffsetDateTime.now(), false, null)) ;
        }       
        
        lookupval_repo.delete(value.get());

        return ResponseEntity.status(200).body(new LookUpValueResponse(value.get().getCode() + " successfully deleted.", OffsetDateTime.now(), true, id)) ;
    }

    public ResponseEntity<?> update_values(UUID id, LookUpValueRequest request){
        LookupValues value = lookupval_repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        value.setDisplayValue(request.getDisplayValue());
        value.setCode(request.getCode());
        value.setActive(request.getActive());

        lookupval_repo.save(value);

        return ResponseEntity.status(200).body(new LookUpValueResponse( "Sucessfully Updated! ", OffsetDateTime.now(),true, value.getId())) ;
    }
}
