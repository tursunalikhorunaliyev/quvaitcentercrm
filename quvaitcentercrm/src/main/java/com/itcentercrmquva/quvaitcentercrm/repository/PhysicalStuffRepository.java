package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalStuff;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalFaceProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffByCategoryProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalStuffRepository extends JpaRepository<PhysicalStuff, Long> {
    boolean existsByPhysicalFace_IdAndStuffCategory_IdAndOrganizations_Id(Long fId , Long cId, Long orgId);

    List<PhysicalStuffProjection> findByOrganizations(Organizations organizations);

    List<PhysicalStuffProjection> findByOrganizationsAndStuffCategory_Id(Organizations organizations, Long id);

    //@Query(nativeQuery = true, value = "SELECT count(*) as countFaces, stuff_category_id as category FROM itcentercrm.physical_stuff where org_id = :orgId group by stuff_category_id;")
    /*@Query("SELECT COUNT(id) as countFaces, ps.stuffCategory ps FROM PhysicalStuff as ps WHERE ps.organizations = ?1 GROUP BY ps.stuffCategory")
    List<PhysicalStuffByCategoryProjection> getPSbyCategory(Organizations organizations);*/

}