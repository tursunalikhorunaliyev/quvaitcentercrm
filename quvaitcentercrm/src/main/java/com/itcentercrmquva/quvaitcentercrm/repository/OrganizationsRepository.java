package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.projection.OrganizationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, Long>{

    boolean existsBygNumber(String gNumber);

    @Query("SELECT o FROM Organizations o")
    List<OrganizationProjection> getAllOrg();
}