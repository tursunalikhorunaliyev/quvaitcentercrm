package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersGroup;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersGroupProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeachersGroupRepository extends JpaRepository<TeachersGroup, Long> {
    Optional<TeachersGroupProjection> findProjectionById(Long id);

    @Query("select t from TeachersGroup t")
    List<TeachersGroupProjection> findProjectionsAll();
}