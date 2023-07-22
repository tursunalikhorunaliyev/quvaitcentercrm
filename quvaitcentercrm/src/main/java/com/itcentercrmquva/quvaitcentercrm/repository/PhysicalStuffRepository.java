package com.itcentercrmquva.quvaitcentercrm.repository;
import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalStuff;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffByCategoryProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalStuffRepository extends JpaRepository<PhysicalStuff, Long> {
    boolean existsByPhysicalFace_IdAndStuffCategory_IdAndOrganizations_Id(Long fId , Long cId, Long orgId);

    List<PhysicalStuffProjection> findByOrganizations(Organizations organizations);

    List<PhysicalStuffProjection> findByOrganizationsAndStuffCategory_Id(Organizations organizations, Long id);

    @Query("SELECT COUNT(*) as countFaces, ps.stuffCategory as category FROM PhysicalStuff as ps WHERE ps.organizations = ?1 GROUP BY ps.stuffCategory")
    List<PhysicalStuffByCategoryProjection> getPSbyCategory(Organizations organizations);

}