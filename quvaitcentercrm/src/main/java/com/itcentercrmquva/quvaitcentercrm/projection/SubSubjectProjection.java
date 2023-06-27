package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;

import java.sql.Timestamp;

public interface SubSubjectProjection {
    Long getId();
    String getName();

    String getDescription();
    SubjectsRecord getSubject();
}
