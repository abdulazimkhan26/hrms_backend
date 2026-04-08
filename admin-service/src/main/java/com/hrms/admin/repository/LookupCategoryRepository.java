package com.hrms.admin.repository;

import com.hrms.admin.entity.LookupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LookupCategoryRepository extends JpaRepository<LookupCategory, UUID> {
    Optional<LookupCategory> findByCode(String code);
    boolean existsByCode(String code);

    @Query("SELECT lc.code FROM LookupCategory lc")
    List<String> findAllCodes();

}
