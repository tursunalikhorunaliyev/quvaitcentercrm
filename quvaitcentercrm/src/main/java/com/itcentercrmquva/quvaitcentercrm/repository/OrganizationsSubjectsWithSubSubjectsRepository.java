package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.OrganizationsSubjectsWithSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;
import com.itcentercrmquva.quvaitcentercrm.projection.OrganizationsSSProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationsSubjectsWithSubSubjectsRepository extends JpaRepository<OrganizationsSubjectsWithSubSubjects, Long> {
    @Query("""
            select (count(o) > 0) from OrganizationsSubjectsWithSubSubjects o
            where o.subject.id = ?1 and o.subSubject.id = ?2 and o.organization = ?3""")
    boolean ifExistsBySSO(Long id, Long id1, Organizations organization);

    @Query("SELECT ss FROM OrganizationsSubjectsWithSubSubjects ss")
    List<OrganizationsSSProjection> getAllOrgSS();



}