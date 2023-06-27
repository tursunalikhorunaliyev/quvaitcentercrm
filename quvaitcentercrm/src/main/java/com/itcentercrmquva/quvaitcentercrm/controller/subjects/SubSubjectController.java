package com.itcentercrmquva.quvaitcentercrm.controller.subjects;
import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.SubSubjectRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.SubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects/sub/")
public class SubSubjectController {
    private SubSubjectRepository subSubjectRepository;
    private JWTGenerator jwtGenerator;
    private UserRepository userRepository;
    private SubjectsRepository subjectsRepository;

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, @RequestParam("subject_id") Long subjectId, @RequestParam("description") String description, HttpServletRequest request) {
        name = name.trim();
        String token = request.getHeader("Authorization");
        String username = jwtGenerator.getUsernameFromToken(token.substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        SubSubject subSubjects = new SubSubject();
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Foydalanuvchi topilmadi"), HttpStatus.BAD_REQUEST);
        }
        if (subjectsRepository.findById(subjectId).isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Tanlangan fan topilmadi"), HttpStatus.BAD_REQUEST);
        }
        if (subSubjectRepository.existsByName(name)) {
            return new ResponseEntity<>(new ResponseResult(false, "Ushbu yo'nalish oldin yaratilgan"), HttpStatus.BAD_REQUEST);
        }
        subSubjects.setName(name.trim());
        subSubjects.setUser(user.get());
        subSubjects.setSubject(subjectsRepository.findById(subjectId).get());
        subSubjects.setDescription(description.trim());
        subSubjectRepository.save(subSubjects);
        return new ResponseEntity<>(new ResponseResult(true, "Yo'nalish yaratildi"), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Collection> getAllSubs(){
        return new ResponseEntity<>(subSubjectRepository.getAllSubSubjects(), HttpStatus.OK);
    }

}

