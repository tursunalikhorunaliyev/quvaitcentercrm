package com.itcentercrmquva.quvaitcentercrm.projection;

import org.springframework.beans.factory.annotation.Value;

public interface SubSubjectProjection {
    Long getId();
    String getName();

    String getDescription();
    SubjectsProjection getSubject();



//http://localhost:8080/api/image/get?id=
    @Value("#{target.imageStore==null?'': 'http://localhost:8080/api/image/get?id='+{target.imageStore.id}}")
    String getImageUrl();


}
