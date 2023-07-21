package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.*;
import com.itcentercrmquva.quvaitcentercrm.repository.InterestsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.RoleRepository;
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

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InterestsService {

    private final InterestsRepository interestsRepository;
    private final JWTGenerator jwtGenerator;

    private final UserRepository userRepository;

    private  final RoleRepository roleRepository;

    public ResponseEntity<List<Interests>> getAllInterests(){
        return ResponseEntity.ok(interestsRepository.findAll());
    }

    public ResponseEntity<ResponseResult> uploadAllInterests(MultipartFile file, HttpServletRequest request){
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
            LinkedList<Interests> interestsLinkedList = new LinkedList<>();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                Interests interests = new Interests();
                interests.setName(row.getCell(0).toString().trim());
                interests.setUser(user.get());
                interestsLinkedList.add(interests);
            }
            interestsRepository.saveAll(interestsLinkedList);
            return new ResponseEntity<>(new ResponseResult(true, "Ma'lumotlar saqlandi"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseResult(false, "Ma'lumotlarni saqlashda xatolik"), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ResponseResult> save(String name, HttpServletRequest request) {

        Optional<Users> usersOptional = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        if (usersOptional.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "User topilmadi"), HttpStatus.BAD_REQUEST);
        }
        if (interestsRepository.existsByName(name.trim())) {
            return new ResponseEntity<>(new ResponseResult(false, "Ma'lumot oldin yaratilgan"), HttpStatus.BAD_REQUEST);
        }

        Interests interests = new Interests();
        interests.setName(name.trim());
        interests.setUser(usersOptional.get());
        interestsRepository.save(interests);

        return new ResponseEntity<>(new ResponseResult(true, "Ma'lumotlar saqlandi"), HttpStatus.OK);

    }

}
