package com.itcentercrmquva.quvaitcentercrm.projection.projections;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;

public interface SubSubjectProjection {
    Long getId();
    String getName();
    Timestamp getTimestamp();
}
