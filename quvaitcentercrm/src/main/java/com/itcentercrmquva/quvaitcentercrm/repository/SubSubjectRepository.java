package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.projection.SubSubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubSubjectRepository extends JpaRepository<SubSubject, Long> {
    boolean existsByName(String name);
    @Query("SELECT s FROM SubSubject s")
    List<SubSubjectProjection> getAllSubSubjects();
}