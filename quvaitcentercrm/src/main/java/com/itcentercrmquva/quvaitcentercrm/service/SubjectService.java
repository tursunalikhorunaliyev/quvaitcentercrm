package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.SubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {

    private JWTGenerator jwtGenerator;
    private UserRepository userRepository;
    private final SubjectsRepository subjectsRepository;

    public ResponseEntity<ResponseResult> save(String name, HttpServletRequest request) {
        name = name.trim();
        String token = request.getHeader("Authorization");
        String username = jwtGenerator.getUsernameFromToken(token.substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        Subjects subjects = new Subjects();
        if (user.isPresent()) {
            if (subjectsRepository.existsByName(name)) {
                return new ResponseEntity<>(new ResponseResult(false, "Ushbu fan oldin yaratilgan"), HttpStatus.BAD_REQUEST);
            }
            subjects.setUser(user.get());
            subjects.setName(name.trim());
            subjectsRepository.save(subjects);
            return new ResponseEntity<>(new ResponseResult(true, "Fan yaratildi"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseResult(false, "Foydalanuvchi topilmadi"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Collection> getAllSubject() {
        return new ResponseEntity<>(subjectsRepository.getAllSubjects(), HttpStatus.OK);
    }
}
