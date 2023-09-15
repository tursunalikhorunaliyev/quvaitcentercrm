package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.entity.*;

import java.sql.Timestamp;

/**
 * Projection for {@link com.itcentercrmquva.quvaitcentercrm.entity.TeachersGroup}
 */
public interface TeachersGroupProjection {
    Long getId();

    Timestamp getTimestamp();

    TeachersSubSubjects getTeachersSubSubjects();

    SSCategory getSsCategory();

    Groups getGroups();

    GroupStatus getGroupStatus();

    Organizations getOrganizations();
}