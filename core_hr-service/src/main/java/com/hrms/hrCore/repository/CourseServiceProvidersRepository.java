package com.hrms.hrCore.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrms.hrCore.entity.CourseServiceProviders;

@Repository
public interface CourseServiceProvidersRepository extends JpaRepository<CourseServiceProviders, UUID> {
    Optional<CourseServiceProviders> findByproviderNumber(String pn);
    Optional<CourseServiceProviders> findByname(String name);
}
