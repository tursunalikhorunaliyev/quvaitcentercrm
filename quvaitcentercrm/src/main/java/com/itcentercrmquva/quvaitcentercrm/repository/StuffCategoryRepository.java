package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.StuffCategory;
import com.itcentercrmquva.quvaitcentercrm.projection.InterestsProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.StuffCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StuffCategoryRepository extends JpaRepository<StuffCategory, Long> {
    boolean existsByName(String name);

    @Query("SELECT sc FROM StuffCategory sc")
    Set<StuffCategoryProjection> findAllStuffCategoryByJPQL();
}