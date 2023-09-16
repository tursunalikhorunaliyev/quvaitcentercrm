package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersGroup;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersGroupProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.*;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherGroupService {

    private final JWTGenerator jwtGenerator;
    private final TeachersGroupRepository teachersGroupRepository;
    private final GroupsRepository groupsRepository;
    private final GroupStatusRepository groupStatusRepository;
    private final SSCategoryRepository sSCategoryRepository;
    private final TeachersSubSubjectsRepository teachersSubSubjectsRepository;

    public ResponseEntity<TeachersGroupProjection> getTeacherGroup(Long tgId) {
        return ResponseEntity.ok(teachersGroupRepository.findProjectionById(tgId).get());
    }

    public ResponseEntity<List<TeachersGroupProjection>> getAllTeacherGroup() {
        return ResponseEntity.ok(teachersGroupRepository.findProjectionsAll());
    }

    public ResponseEntity<ResponseResult> create(Long gId, Long gsId, Long sscId, Long tssId, HttpServletRequest request) {
        Users users = jwtGenerator.getUserFromRequest(request);
        TeachersGroup tg = new TeachersGroup();
        tg.setUsers(users);
        tg.setOrganizations(users.getOrganization());
        tg.setGroups(groupsRepository.findById(gId).get());
        tg.setGroupStatus(groupStatusRepository.findById(gsId).get());
        tg.setSsCategory(sSCategoryRepository.findById(sscId).get());
        tg.setTeachersSubSubjects(teachersSubSubjectsRepository.findById(tssId).get());
        teachersGroupRepository.save(tg);
        return new ResponseEntity<>(new ResponseResult(true, "Saqlandi"), HttpStatus.OK);
    }
}
