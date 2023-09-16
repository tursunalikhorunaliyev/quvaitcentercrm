package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Students;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.projection.StudentsProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.StudentsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.TeachersSubSubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentsService {

    private final JWTGenerator generator;
    private final TeachersSubSubjectsRepository teachersSubSubjectsRepository;
    private final StudentsRepository studentsRepository;

    public ResponseEntity<List<StudentsProjection>> getAll() {
        return ResponseEntity.ok(studentsRepository.findAllProjections());
    }

    public ResponseEntity<StudentsProjection> getStudent(Long id) {
        StudentsProjection students = studentsRepository.findProjectionById(id).orElse(null);
        if (students == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(students);
    }

    public ResponseEntity<ResponseResult> create(String firstname, String lastname, String middlename, String phone, Long tssId, HttpServletRequest request) {
        Users users = generator.getUserFromRequest(request);
        Students students = new Students();
        students.setFirstname(firstname);
        students.setLastname(lastname);
        students.setMiddlename(middlename);
        students.setPhone(phone);
        students.setUser(users);
        Optional<TeachersSubSubjects> st = teachersSubSubjectsRepository.findById(tssId);
        if (st.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "tss topilmadi"), HttpStatus.BAD_REQUEST);
        }
        students.setTeachersSubSubjects(st.get());
        students.setOrganizations(users.getOrganization());
        studentsRepository.save(students);
        return new ResponseEntity<>(new ResponseResult(true, "Student qo'shildi"), HttpStatus.OK);
    }
}
