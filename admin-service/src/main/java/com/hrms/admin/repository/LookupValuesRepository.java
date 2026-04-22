package com.hrms.admin.repository;

import com.hrms.admin.entity.LookupValues;
import com.hrms.admin.projection.LookupValuesProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LookupValuesRepository extends JpaRepository<LookupValues, UUID> {
    boolean existsByCode(String code);
    boolean existsByCategoryId(UUID categoryId);

    List<LookupValuesProjection> findByCategoryId(UUID categoryId);
}
