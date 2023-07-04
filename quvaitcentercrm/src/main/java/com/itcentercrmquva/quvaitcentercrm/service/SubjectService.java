package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Subjects;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.SubjectsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
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

    public ResponseEntity<ResponseResult> readAndSaveFromExcel(MultipartFile file){

        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            LinkedList<Subjects> subjectsList = new LinkedList<>();
            for(int i=0; i<sheet.getPhysicalNumberOfRows(); i++){
                XSSFRow row = sheet.getRow(i);

                Subjects subjects = new Subjects();
                subjects.setName(row.getCell(0).toString().trim());
                subjectsList.add(subjects);
            }
            subjectsRepository.saveAll(subjectsList);
            return new ResponseEntity<>(new ResponseResult(true, "Ma'lumotlar saqlandi"), HttpStatus.OK);
        } catch (IOException e) {
            return  new ResponseEntity<>(new ResponseResult(false,"Ma'lumotlarni saqlashda xatolik"), HttpStatus.BAD_REQUEST);
        }

    }
}
