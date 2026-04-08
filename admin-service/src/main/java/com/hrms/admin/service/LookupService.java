package com.hrms.admin.service;

import com.hrms.admin.dto.LookUpCategoryRequest;
import com.hrms.admin.dto.LookUpCategoryResponse;
import com.hrms.admin.dto.LookUpValueRequest;
import com.hrms.admin.dto.LookUpValueResponse;
import com.hrms.admin.entity.LookupCategories;
import com.hrms.admin.entity.LookupValues;
import com.hrms.admin.repository.LookupCategoriesRepository;
import com.hrms.admin.repository.LookupValuesRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class LookupService {
    private LookupCategoriesRepository lookupcat_repo;
    private LookupValuesRepository lookupval_repo;

    public LookupService(LookupCategoriesRepository lookupcat_repo, LookupValuesRepository lookupval_repo){
        this.lookupcat_repo = lookupcat_repo;
        this.lookupval_repo = lookupval_repo;
    }

// ---LookUpCategories---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public List<String> allCategories(){
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

        LookupCategories saved = lookupcat_repo.save(category);
        return new LookUpCategoryResponse("Category Created Successfully!", request.getCode(), request.getLabel(), OffsetDateTime.now(), true);
    }

    public LookUpCategoryResponse delete_category(String code){
        LookupCategories category = lookupcat_repo.findByCode(code).orElseThrow(()-> new RuntimeException(""));
        lookupcat_repo.delete(category);

        return new LookUpCategoryResponse(code + "deleted successfully", "", "", OffsetDateTime.now(), true);
    }

    public LookUpCategoryResponse update_category(String code, LookUpCategoryRequest request){
        LookupCategories category = lookupcat_repo.findByCode(code)
                .orElseThrow(()-> new RuntimeException(""));

        category.setCode(request.getCode());
        category.setLabel(request.getLabel());
        category.setDescription(request.getDescription());

        lookupcat_repo.save(category);

        return new LookUpCategoryResponse(code + " successfully updated to " + request.getCode(), request.getCode(), request.getLabel(), OffsetDateTime.now(), true);
    }

// ---LookUpValues-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public List<String> all_values(String category_code){
        LookupCategories category = lookupcat_repo
                .findByCode(category_code)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return lookupval_repo.findByCategory(category);
    }

    public LookUpValueResponse create_values(LookUpValueRequest request){
        if(lookupval_repo.existsByCode(request.getCode())){
            return new LookUpValueResponse("The Value Code already exists!", "", OffsetDateTime.now(), false );
        }
        LookupCategories category = lookupcat_repo
                .findByCode(request.getCategoryCode())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        LookupValues values = new LookupValues();
        values.setDisplay_value(request.getDisplayValue());
        values.setCode(request.getCode());
        values.setCategory(category);
        values.setIs_active(request.is_active());

        LookupValues saved = lookupval_repo.save(values);
        return new LookUpValueResponse(request.getCode() + " value successsfully added.", request.getCode(), OffsetDateTime.now(), true);
    }

    public LookUpValueResponse delete_values(String code){
        LookupValues value = lookupval_repo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("No existing value " + code + " found!"));

        lookupval_repo.delete(value);

        return new LookUpValueResponse(code + " successfully deleted.", code, OffsetDateTime.now(), true) ;
    }

    public LookUpValueResponse update_values(String code, LookUpValueRequest request){
        LookupValues value = lookupval_repo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("not found"));
        System.out.println(value);

        value.setDisplay_value(request.getDisplayValue());
        value.setCode(request.getCode());
        value.setCategory(value.getCategory());
        value.setIs_active(request.is_active());

        lookupval_repo.save(value);

        return new LookUpValueResponse(code + " successfully updated to " + value.getCode(), value.getCode(), OffsetDateTime.now(),true) ;
    }

}
