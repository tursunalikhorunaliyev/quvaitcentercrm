package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Roles;
import com.itcentercrmquva.quvaitcentercrm.entity.SubSubject;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.RoleRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.SubSubjectRepository;
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
public class SubSubjectService {

    private SubSubjectRepository subSubjectRepository;
    private JWTGenerator jwtGenerator;
    private UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<ResponseResult> save(String name, HttpServletRequest request) throws IOException {
        name = name.trim();
        String token = request.getHeader("Authorization");
        String username = jwtGenerator.getUsernameFromToken(token.substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Foydalanuvchi topilmadi"), HttpStatus.BAD_REQUEST);
        }
        Roles superAdmin = roleRepository.findByName("SUPERADMIN").get();
        if (!user.get().getRoles().contains(superAdmin)) {
            return new ResponseEntity<>(new ResponseResult(false, "Ushbu operatsiyani amalga oshira olmaysiz"), HttpStatus.BAD_REQUEST);
        }


        SubSubject subSubjects = new SubSubject();
        if (subSubjectRepository.existsByName(name)) {
            return new ResponseEntity<>(new ResponseResult(false, "Ushbu yo'nalish oldin yaratilgan"), HttpStatus.BAD_REQUEST);
        }

        subSubjects.setName(name.trim());
        subSubjects.setUser(user.get());

        subSubjectRepository.save(subSubjects);
        return new ResponseEntity<>(new ResponseResult(true, "Yo'nalish yaratildi"), HttpStatus.OK);
    }

    public ResponseEntity<Collection> getAllSubs() {
        return new ResponseEntity<>(subSubjectRepository.getAllSubSubjects(), HttpStatus.OK);
    }

    public  ResponseEntity<ResponseResult> readAndSaveFromExcel(MultipartFile file, HttpServletRequest request){

        String token = request.getHeader("Authorization");
        String username = jwtGenerator.getUsernameFromToken(token.substring(7));
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Foydalanuvchi topilmadi"), HttpStatus.BAD_REQUEST);
        }
        Roles superAdmin = roleRepository.findByName("SUPERADMIN").get();
        if (!user.get().getRoles().contains(superAdmin)) {
            return new ResponseEntity<>(new ResponseResult(false, "Ushbu operatsiyani amalga oshira olmaysiz"), HttpStatus.BAD_REQUEST);
        }

        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            LinkedList<SubSubject> subjectsList = new LinkedList<>();

            for(int i=0; i<sheet.getPhysicalNumberOfRows(); i++){
                XSSFRow row = sheet.getRow(i);
                SubSubject subSubjects = new SubSubject();
                subSubjects.setName(row.getCell(0).toString().trim());
                subSubjects.setUser(user.get());
                subjectsList.add(subSubjects);
            }
            subSubjectRepository.saveAll(subjectsList);
            return new ResponseEntity<>(new ResponseResult(true, "Ma'lumotlar saqlandi"), HttpStatus.OK);
        } catch (IOException e) {
            return  new ResponseEntity<>(new ResponseResult(false,"Ma;lumotlarni saqlashda xatolik"), HttpStatus.BAD_REQUEST);
        }

    }
}
