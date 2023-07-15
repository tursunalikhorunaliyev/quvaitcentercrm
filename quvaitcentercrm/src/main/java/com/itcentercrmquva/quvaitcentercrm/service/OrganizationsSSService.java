package com.itcentercrmquva.quvaitcentercrm.service;
import com.itcentercrmquva.quvaitcentercrm.dto.OrganizationSubjects;
import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.*;
import com.itcentercrmquva.quvaitcentercrm.projection.OrganizationsSSProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.OrganizationsSubjectsWithSubSubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.SubSubjectRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.SubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationsSSService {

    private final JWTGenerator jwtGenerator;

    private final UserRepository userRepository;
    private final SubjectsRepository subjectsRepository;
    private final SubSubjectRepository subSubjectRepository;
    private final OrganizationsSubjectsWithSubSubjectsRepository organizationsSubjectsWithSubSubjectsRepository;

    public ResponseEntity<ResponseResult> createSS(Long sid, Long ssid, String description, MultipartFile image, HttpServletRequest request){

        String username = jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "User not found"), HttpStatus.BAD_REQUEST);
        } else if (user.get().getOrganization()==null) {
            return new ResponseEntity<>(new ResponseResult(false, "Organization not found"), HttpStatus.BAD_REQUEST);
        }
        if(organizationsSubjectsWithSubSubjectsRepository.ifExistsBySSO(sid, ssid, user.get().getOrganization())){
            return new ResponseEntity<>(new ResponseResult(false, "This subject and sub subjects are created by this organizations"), HttpStatus.BAD_REQUEST);
        }
        OrganizationsSubjectsWithSubSubjects oSS = new OrganizationsSubjectsWithSubSubjects();
        Optional<Subjects> subjects = subjectsRepository.findById(sid);
        if(subjects.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "Subject not found"), HttpStatus.BAD_REQUEST);
        }
        Optional<SubSubject> subSubject = subSubjectRepository.findById(ssid);
        if(subSubject.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "Sub Subject not found"), HttpStatus.BAD_REQUEST);
        }
        ImageStore imageStore = new ImageStore();
        try {
            imageStore.setContent(image.getBytes());
            imageStore.setFilename(image.getOriginalFilename());
            imageStore.setUsers(user.get());
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseResult(false, "Something went wrong with image uploading"), HttpStatus.BAD_REQUEST);
        }


        oSS.setSubject(subjects.get());
        oSS.setSubSubject(subSubject.get());
        oSS.setOrganization(user.get().getOrganization());
        oSS.setUsers(user.get());
        oSS.setImageStore(imageStore);
        oSS.setDescription(description.trim());
        organizationsSubjectsWithSubSubjectsRepository.save(oSS);
        return new ResponseEntity<>(new ResponseResult(true, "Ma'lumotlar saqlandi"), HttpStatus.OK);
    }

    public ResponseEntity<List<OrganizationsSSProjection>> getAllOrganizationsSS(HttpServletRequest request){
        String username = jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7));
        Optional<Users> user = userRepository.findByUsername(username);

        return new ResponseEntity<>(organizationsSubjectsWithSubSubjectsRepository.findByOrganization(user.get().getOrganization()), HttpStatus.OK);
    }

    public ResponseEntity<Set<OrganizationSubjects>> getOrgSubjects(HttpServletRequest request){
        String username = jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        Set<OrganizationSubjects> organizationSubjects = organizationsSubjectsWithSubSubjectsRepository.getOrgSubjects(user.get().getOrganization()).stream().map(e->new OrganizationSubjects(e.getId(), e.getName())).collect(Collectors.toSet());
        return  new ResponseEntity<>(organizationSubjects, HttpStatus.OK);
    }

    public ResponseEntity<List<OrganizationsSSProjection>> getAllOrganizationSSBySubject(HttpServletRequest request, Long subId){
        String username = jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        return new ResponseEntity<>(organizationsSubjectsWithSubSubjectsRepository.findByOrganizationAndSubject_Id(user.get().getOrganization(), subId), HttpStatus.OK);
    }
}
