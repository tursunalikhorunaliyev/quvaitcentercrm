package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.TeachersSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.projection.OnlyTeacherSubSubjectProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersSubSubjectsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeachersSubSubjectsRepository extends JpaRepository<TeachersSubSubjects, Long> {

    Optional<OnlyTeacherSubSubjectProjection> findPById(Long id);

    @Query("select t from TeachersSubSubjects t where t.organizations.id = ?1")
    List<TeachersSubSubjectsProjection> findByOrganizations_Id(Long id);
}