package com.itcentercrmquva.quvaitcentercrm.projection;

import java.util.Set;

public interface TeachersSubSubjectsProjection {
    Long getId();
    Set<OrganizationsSSProjection> getOrgSubSubjects();
}
