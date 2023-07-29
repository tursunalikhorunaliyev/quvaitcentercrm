package com.itcentercrmquva.quvaitcentercrm.projection;
import org.springframework.beans.factory.annotation.Value;



public interface OrganizationsSSProjection {
    Long getId();
    @Value("http://192.168.1.3:8080/api/image/get?id=#{target.imageStore.id}")
    String getImageStore();
    SubjectsProjection getSubject();
    SubSubjectProjection getSubSubject();
    OrganizationProjection getOrganization();
    String getDescription();
}
