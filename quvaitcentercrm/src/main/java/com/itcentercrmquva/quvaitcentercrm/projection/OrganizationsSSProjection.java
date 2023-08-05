package com.itcentercrmquva.quvaitcentercrm.projection;
import org.springframework.beans.factory.annotation.Value;



public interface OrganizationsSSProjection {
    Long getId();
    @Value("https://1eb2-213-230-78-214.ngrok-free.app/api/image/get?id=#{target.imageStore.id}")
    String getImageStore();
    SubjectsProjection getSubject();
    SubSubjectProjection getSubSubject();
    OrganizationProjection getOrganization();
    String getDescription();
}
