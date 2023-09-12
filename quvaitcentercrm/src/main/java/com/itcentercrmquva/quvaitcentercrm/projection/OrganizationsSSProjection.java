package com.itcentercrmquva.quvaitcentercrm.projection;
import org.springframework.beans.factory.annotation.Value;



public interface OrganizationsSSProjection {
    Long getId();
    @Value("#{target.imageStore.id}")
    Long getImageStore();
    SubSubjectProjection getSubSubject();
    SubjectsProjection getSubject();
    String getDescription();
}
