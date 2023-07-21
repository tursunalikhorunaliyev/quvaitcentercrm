package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.OrganizationsSubjectsWithSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;
import com.itcentercrmquva.quvaitcentercrm.projection.OrganizationsSSProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.SubjectsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public interface OrganizationsSubjectsWithSubSubjectsRepository extends JpaRepository<OrganizationsSubjectsWithSubSubjects, Long> {
    List<OrganizationsSSProjection> findByOrganizationAndSubject_Id(Organizations organization, Long id);
    List<OrganizationsSSProjection> findByOrganization(Organizations organization);
    @Query("""
            select (count(o) > 0) from OrganizationsSubjectsWithSubSubjects o
            where o.subject.id = ?1 and o.subSubject.id = ?2 and o.organization = ?3""")
    boolean ifExistsBySSO(Long id, Long id1, Organizations organization);

    @Query("SELECT ss FROM OrganizationsSubjectsWithSubSubjects ss")
    List<OrganizationsSSProjection> getAllOrgSS();

    @Query("""
            select o.subject from OrganizationsSubjectsWithSubSubjects o
            where o.organization = ?1 group by o.subject""")
    List<Subjects> getOrgSubjects(Organizations organizations);








}