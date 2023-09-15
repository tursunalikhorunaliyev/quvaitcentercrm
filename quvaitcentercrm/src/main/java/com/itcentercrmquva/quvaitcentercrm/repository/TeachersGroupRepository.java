package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.TeachersGroup;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersGroupProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeachersGroupRepository extends JpaRepository<TeachersGroup, Long> {
    Optional<TeachersGroupProjection> findProjectionById(Long id);

    List<TeachersGroupProjection> findAllProjection();
}