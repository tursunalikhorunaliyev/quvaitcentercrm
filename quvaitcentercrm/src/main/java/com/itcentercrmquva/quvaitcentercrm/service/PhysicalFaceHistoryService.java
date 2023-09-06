package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.entity.Interests;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalFace;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalFaceHistory;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.PhysicalFaceHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PhysicalFaceHistoryService {

    private final PhysicalFaceHistoryRepository physicalFaceHistoryRepository;

    public PhysicalFaceHistory writeHistory(PhysicalFace face, Users updater) {
        final PhysicalFaceHistory history = new PhysicalFaceHistory();
        history.setPhysicalFace(face.getId());
        history.setUpdaterUser(updater.getId());
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
        history.setPhoto(face.getPhoto().getId());
        history.setEducationLevel(face.getEducationLevel().getId());
        history.setTimestamp(face.getTimestamp());
        history.setUser(face.getUser().getId());
        StringBuilder interestsString = new StringBuilder();
        for (int i = 0; i < face.getInterests().size(); i++) {
            Interests o = (Interests) face.getInterests().toArray()[i];
            if (i == face.getInterests().size() - 1) {
                interestsString.append(o.getName());
            } else {
                interestsString.append(o.getName()).append(",");
            }
        }
        history.setInterests(interestsString.toString());

        return physicalFaceHistoryRepository.save(history);
    }

}
