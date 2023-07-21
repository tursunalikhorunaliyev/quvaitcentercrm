package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.EducationLevel;
import com.itcentercrmquva.quvaitcentercrm.projection.EducationLevelProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.SubjectsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {
    boolean existsByName(String name);
    @Query("SELECT e FROM EducationLevel e")
    List<EducationLevelProjection> getAllSubjects();
}