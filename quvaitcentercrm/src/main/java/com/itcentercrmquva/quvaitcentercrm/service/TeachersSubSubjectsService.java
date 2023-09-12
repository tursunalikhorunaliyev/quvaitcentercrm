package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.OrganizationsSubjectsWithSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalStuff;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersSubSubjectProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.OrganizationsSubjectsWithSubSubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.PhysicalStuffRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.TeachersSubSubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@AllArgsConstructor
public class TeachersSubSubjectsService {

    private final PhysicalStuffRepository physicalStuffRepository;
    private final JWTGenerator jwtGenerator;
    private final OrganizationsSubjectsWithSubSubjectsRepository organizationsSubjectsWithSubSubjectsRepository;
    private final TeachersSubSubjectsRepository teachersSubSubjectsRepository;


    public ResponseEntity<ResponseResult> create(Long physicalFaceStuff, String ssIDs, HttpServletRequest request) {
        Users users = jwtGenerator.getUserFromRequest(request);
        TeachersSubSubjects teachersSubSubjects = new TeachersSubSubjects();
        Optional<PhysicalStuff> physicalStuff = physicalStuffRepository.findById(physicalFaceStuff);
        if (physicalStuff.isPresent() && physicalStuff.get().getStuffCategory().getId() == 3L) {
            teachersSubSubjects.setPhysicalStuff(physicalStuff.get());
        } else {
            return new ResponseEntity<>(new ResponseResult(false, "Bunday o'qituvchi topilmadi"), HttpStatus.BAD_REQUEST);
        }
        teachersSubSubjects.setUsers(users);
        teachersSubSubjects.setOrganizations(users.getOrganization());
        if (ssIDs.length() == 1) {
            Optional<OrganizationsSubjectsWithSubSubjects> orgSS = organizationsSubjectsWithSubSubjectsRepository.findById(Long.parseLong(ssIDs));
            if (orgSS.isPresent()) {
                teachersSubSubjects.setOrgSubSubjects(Collections.singleton(orgSS.get()));
            } else {
                return new ResponseEntity<>(new ResponseResult(false, "Bunday fan yo'nalish topilmadi"), HttpStatus.BAD_REQUEST);
            }
        } else {
            List<Long> orgSSData = Arrays.stream(ssIDs.split(",")).map(Long::parseLong).toList();

            Set<OrganizationsSubjectsWithSubSubjects> orgSSList = organizationsSubjectsWithSubSubjectsRepository.getByInIds(orgSSData);
            teachersSubSubjects.setOrgSubSubjects(orgSSList);
        }
        teachersSubSubjectsRepository.save(teachersSubSubjects);
        return ResponseEntity.ok(new ResponseResult(true, "Barcha ma'lumotlar saqlandi"));

    }

    public ResponseEntity<TeachersSubSubjectProjection> getTeacherSubSubject(Long id) {
        return ResponseEntity.ok(teachersSubSubjectsRepository.findPById(id).get());
    }

}
