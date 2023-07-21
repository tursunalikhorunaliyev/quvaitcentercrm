package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Interests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InterestsRepository extends JpaRepository<Interests, Long> {
    boolean existsByName(String name);
    @Query("SELECT i FROM Interests i WHERE i.id IN (:ids)")
    Set<Interests> findInterestssByIds(@Param("ids") List<Long> ids);

}