package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Groups;
import com.itcentercrmquva.quvaitcentercrm.entity.SSCategory;
import com.itcentercrmquva.quvaitcentercrm.repository.SSCategoryRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;

@Service
@AllArgsConstructor
public class SSCategoryService {

    private final SSCategoryRepository ssCategoryRepository;

    public ResponseEntity<Object> create(String name) {
        final SSCategory ssCategory = new SSCategory();
        ssCategory.setName(name);
        ssCategoryRepository.save(ssCategory);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<Object> xlsx(MultipartFile file) {
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            LinkedList<SSCategory> categoryLinkedList = new LinkedList<>();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                final SSCategory ssCategory = new SSCategory();
                ssCategory.setName(row.getCell(0).toString().trim());
                categoryLinkedList.add(ssCategory);
            }
            ssCategoryRepository.saveAll(categoryLinkedList);
            return new ResponseEntity<>(new ResponseResult(true, "Saqlandi"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseResult(false, "Saqlanmadi"), HttpStatus.BAD_REQUEST);
        }
    }
}
