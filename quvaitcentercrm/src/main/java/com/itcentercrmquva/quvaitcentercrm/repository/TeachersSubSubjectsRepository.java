package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.TeachersSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersSubSubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeachersSubSubjectsRepository extends JpaRepository<TeachersSubSubjects, Long> {

    Optional<TeachersSubSubjectProjection> findPById(Long id);
}