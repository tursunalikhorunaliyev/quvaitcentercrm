package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalFace;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalFaceHistory;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.PhysicalFaceHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhysicalFaceHistoryService {

    private final PhysicalFaceHistoryRepository physicalFaceHistoryRepository;

    public PhysicalFaceHistory writeHistory(PhysicalFace face, Users updater) {
        final PhysicalFaceHistory history = new PhysicalFaceHistory();
        history.setPhysicalFace(face);
        history.setUpdaterUser(updater);
        history.setFirstName(face.getFirstName());
        history.setLastName(face.getLastName());
        history.setMiddleName(face.getMiddleName());
        history.setBirthday(face.getBirthday());
        history.setPersonalIdentification(face.getPersonalIdentification());
        history.setAddress(face.getAddress());
        history.setPrimaryPhone(face.getPrimaryPhone());
        history.setSecondaryPhone(face.getSecondaryPhone());
        history.setTelegramUsername(face.getTelegramUsername());
        history.setInstagramUsername(face.getInstagramUsername());
        history.setPhoto(face.getPhoto());
        history.setEducationLevel(face.getEducationLevel());
        history.setInterests(face.getInterests());
        history.setTimestamp(face.getTimestamp());
        history.setUser(face.getUser());
        return physicalFaceHistoryRepository.save(history);
    }

}
