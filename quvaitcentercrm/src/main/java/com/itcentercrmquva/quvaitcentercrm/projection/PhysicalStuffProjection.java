package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalFaceProjection;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface PhysicalStuffProjection {
    Long getId();
    PhysicalFaceProjection getPhysicalFace();
    StuffCategoryProjection getStuffCategory();
    LocalDate getStartDate();
}
