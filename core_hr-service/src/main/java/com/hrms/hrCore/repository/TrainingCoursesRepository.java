package com.hrms.hrCore.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrms.hrCore.entity.TrainingCourses;

@Repository
public interface TrainingCoursesRepository extends JpaRepository<TrainingCourses, UUID> {
   Optional<TrainingCourses> findByCourseCode(String code);    
}
