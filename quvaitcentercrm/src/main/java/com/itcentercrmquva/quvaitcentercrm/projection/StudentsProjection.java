package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersSubSubjects;

import java.sql.Timestamp;

/**
 * Projection for {@link com.itcentercrmquva.quvaitcentercrm.entity.Students}
 */
public interface StudentsProjection {
    Long getId();

    String getFirstname();

    String getLastname();

    String getMiddlename();

    String getPhone();

    Timestamp getTimestamp();

    TeachersSubSubjectsProjection getTeachersSubSubjects();
}