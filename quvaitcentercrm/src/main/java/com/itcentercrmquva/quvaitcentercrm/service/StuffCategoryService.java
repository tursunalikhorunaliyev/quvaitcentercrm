package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Roles;
import com.itcentercrmquva.quvaitcentercrm.entity.StuffCategory;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.projection.StuffCategoryProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.RoleRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.StuffCategoryRepository;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StuffCategoryService {

    private final StuffCategoryRepository stuffCategoryRepository;
    private final JWTGenerator jwtGenerator;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public ResponseEntity<Set<StuffCategoryProjection>> getAllStuff() {
        return ResponseEntity.ok(stuffCategoryRepository.findAllStuffCategoryByJPQL());
    }

    public ResponseEntity<ResponseResult> uploadAllStuffCategories(MultipartFile file, HttpServletRequest request) {
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
            LinkedList<StuffCategory> stuffCategoryLinkedList = new LinkedList<>();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                StuffCategory stuffCategory = new StuffCategory();
                stuffCategory.setName(row.getCell(0).toString().trim());
                stuffCategory.setUser(user.get());
                stuffCategoryLinkedList.add(stuffCategory);
            }
            stuffCategoryRepository.saveAll(stuffCategoryLinkedList);
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
        if (stuffCategoryRepository.existsByName(name.trim())) {
            return new ResponseEntity<>(new ResponseResult(false, "Ma'lumot oldin yaratilgan"), HttpStatus.BAD_REQUEST);
        }

        StuffCategory stuffCategory = new StuffCategory();
        stuffCategory.setName(name.trim());
        stuffCategory.setUser(usersOptional.get());
        stuffCategoryRepository.save(stuffCategory);

        return new ResponseEntity<>(new ResponseResult(true, "Ma'lumotlar saqlandi"), HttpStatus.OK);

    }

}
