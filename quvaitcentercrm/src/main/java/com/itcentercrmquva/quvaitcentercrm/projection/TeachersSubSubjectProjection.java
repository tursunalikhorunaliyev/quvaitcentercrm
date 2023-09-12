package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.entity.OrganizationsSubjectsWithSubSubjects;

import java.util.Set;

public interface TeachersSubSubjectProjection {
    Long getId();
    PhysicalStuffProjection getPhysicalStuff();

    Set<OrganizationsSSProjection> getOrgSubSubjects();
}
