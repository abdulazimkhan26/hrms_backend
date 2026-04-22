package com.hrms.admin.repository;

import com.hrms.admin.entity.LookupCategories;
import com.hrms.admin.projection.LookupCategoryProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LookupCategoriesRepository extends JpaRepository<LookupCategories, UUID> {
    Optional<LookupCategories> findByCode(String code);
    boolean existsByCode(String code);

    @Query("SELECT lc.id as id, lc.code as code, lc.label as label, lc.description as description FROM LookupCategories lc")
    List<LookupCategoryProjection> findAllCodes();

}
