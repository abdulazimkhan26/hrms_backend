package com.hrms.admin.repository;

import com.hrms.admin.entity.LookupCategories;
import com.hrms.admin.entity.LookupValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LookupValuesRepository extends JpaRepository<LookupValues, UUID> {
    boolean existsByCode(String code);
    Optional<LookupValues> findByCode(String code);

    @Query("SELECT lv.code FROM LookupValues lv")
    List<String> findByCategory(LookupCategories category);
}
