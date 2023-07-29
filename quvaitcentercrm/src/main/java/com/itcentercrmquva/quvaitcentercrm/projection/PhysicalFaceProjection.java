package com.itcentercrmquva.quvaitcentercrm.projection;

import com.itcentercrmquva.quvaitcentercrm.entity.EducationLevel;
import com.itcentercrmquva.quvaitcentercrm.entity.Interests;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PhysicalFaceProjection {
    Long getId();

    String getFirstName();
    String getLastName();
    @Value("#{target.middleName==null?'': {target.middleName}}")
    String getMiddleName();
    LocalDate getBirthday();
    String getPersonalIdentification();

    String getAddress();
    String getPrimaryPhone();

    @Value("#{target.secondaryPhone==null?'': {target.secondaryPhone}}")
    String getSecondaryPhone();

    @Value("#{target.telegramUsername==null?'': {target.telegramUsername}}")
    String getTelegramUsername();
    @Value("#{target.instagramUsername==null?'': {target.instagramUsername}}")
    String getInstagramUsername();

    @Value("http://192.168.1.3:8080/api/image/get?id=#{target.photo.id}")
    String getPhoto();

    EducationLevelProjection getEducationLevel();

    Set<InterestsProjection> getInterests();

}
