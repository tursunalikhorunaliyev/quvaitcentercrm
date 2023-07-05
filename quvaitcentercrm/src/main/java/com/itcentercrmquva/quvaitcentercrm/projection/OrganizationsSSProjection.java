package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;
import org.springframework.beans.factory.annotation.Value;


public interface OrganizationsSSProjection {
    Long getId();

    SubjectsProjection getSubject();

    OrganizationProjection getOrganization();

    SubSubjectProjection getSubSubject();


    String getDescription();

    @Value("http://localhost:8080/api/image/get?id=#{target.imageStore.id}")
    String getImageStore();

}
