package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.projection.projections.SubSubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubSubjectRepository extends JpaRepository<SubSubject, Long> {
    boolean existsByName(String name);
    @Query("SELECT s FROM SubSubject s")
    List<SubSubjectProjection> getAllSubSubjects();
}