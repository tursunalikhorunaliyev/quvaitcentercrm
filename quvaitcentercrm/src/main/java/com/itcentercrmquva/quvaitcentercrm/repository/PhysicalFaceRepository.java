package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalFace;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalFaceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhysicalFaceRepository extends JpaRepository<PhysicalFace, Long> {
    boolean existsByPersonalIdentification(String personalIdentification);

    @Query("select p from PhysicalFace p")
    List<PhysicalFaceProjection> findAllFaces();
    @Query("select p from PhysicalFace p where p.id = ?1")
    Optional<PhysicalFaceProjection> findByIdByQuery(Long id);




}