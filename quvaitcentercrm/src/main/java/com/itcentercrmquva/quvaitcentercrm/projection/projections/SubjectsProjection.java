package com.itcentercrmquva.quvaitcentercrm.projection.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;



public interface SubjectsProjection {
     Long getId();
     String getName();
     Timestamp getTimestamp();
}