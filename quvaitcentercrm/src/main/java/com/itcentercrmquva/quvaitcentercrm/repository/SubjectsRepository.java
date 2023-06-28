package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;
import com.itcentercrmquva.quvaitcentercrm.projection.SubjectsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

    boolean existsByName(String name);
    @Query("SELECT s FROM Subjects s")
    List<SubjectsProjection> getAllSubjects();
}