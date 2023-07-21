package com.itcentercrmquva.quvaitcentercrm.projection;
import org.springframework.beans.factory.annotation.Value;



public interface OrganizationsSSProjection {
    Long getId();
    @Value("http://localhost:8080/api/image/get?id=#{target.imageStore.id}")
    String getImageStore();
    SubjectsProjection getSubject();
    SubSubjectProjection getSubSubject();
    OrganizationProjection getOrganization();
    String getDescription();
}
