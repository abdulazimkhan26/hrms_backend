package com.hrms.admin.repository;

import com.hrms.admin.entity.LookupCategory;
import com.hrms.admin.entity.LookupValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LookupValueRepository extends JpaRepository<LookupValue, UUID> {
    boolean existsByCode(String code);
    Optional<LookupValue> findByCode(String code);

    @Query("SELECT lv.code FROM LookupValue lv")
    List<String> findByCategory(LookupCategory category);
}
