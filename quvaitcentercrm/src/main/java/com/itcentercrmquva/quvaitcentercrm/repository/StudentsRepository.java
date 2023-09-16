package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Students;
import com.itcentercrmquva.quvaitcentercrm.projection.StudentsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students, Long> {

    @Query("select t from Students t")
    List<StudentsProjection> findAllProjections();

    @Query("select t from Students t where t.id = :id")
    Optional<StudentsProjection> findProjectionById(Long id);
}