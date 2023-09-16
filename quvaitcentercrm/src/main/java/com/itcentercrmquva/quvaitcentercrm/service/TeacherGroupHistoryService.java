package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.entity.TeacherGroupHistory;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersGroup;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.TeacherGroupHistoryRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Service
@AllArgsConstructor
public class TeacherGroupHistoryService {

    private final JWTGenerator jwtGenerator;
    private final TeacherGroupHistoryRepository teacherGroupHistoryRepo;

    public void save(String description, TeachersGroup tg, HttpServletRequest request) {
        Users users = jwtGenerator.getUserFromRequest(request);
        TeacherGroupHistory tgh = new TeacherGroupHistory();
        tgh.setDescription(description);
        tgh.setUpdater(users);
        tgh.setTgId(tg.getId());

        tgh.setTeachersSubSubjects(tg.getTeachersSubSubjects());
        tgh.setSsCategory(tg.getSsCategory());
        tgh.setGroups(tg.getGroups());
        tgh.setGroupStatus(tg.getGroupStatus());
        tgh.setOrganizations(tg.getOrganizations());
        tgh.setUsers(tg.getUsers());
        tgh.setTimestamp(tg.getTimestamp());
        teacherGroupHistoryRepo.save(tgh);
    }
}
