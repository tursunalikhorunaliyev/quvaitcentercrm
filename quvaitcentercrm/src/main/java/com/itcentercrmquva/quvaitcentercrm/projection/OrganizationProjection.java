package com.itcentercrmquva.quvaitcentercrm.projection;

import org.springframework.beans.factory.annotation.Value;

public interface OrganizationProjection {
    Long getId();
    String getName();
    String getAddress();
    String getTel1();
    @Value("#{target.tel2==null?'': {target.tel2}}")
    String getTel2();
    @Value("#{target.email==null?'': {target.email}}")
    String getEmail();
    String getINN();
    String getGNumber();
    @Value("https://1eb2-213-230-78-214.ngrok-free.app/api/image/get?id=#{target.gPhoto.id}")
    String getImage();

}
