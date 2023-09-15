package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Groups;
import com.itcentercrmquva.quvaitcentercrm.entity.Interests;
import com.itcentercrmquva.quvaitcentercrm.repository.GroupsRepository;
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
import java.util.List;

@Service
@AllArgsConstructor
public class GroupsService {

    private final GroupsRepository groupsRepository;

    public ResponseEntity<Object> create(String name) {
        final Groups groups = new Groups();
        groups.setName(name);
        groupsRepository.save(groups);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<List<Groups>> getAll(){
        return ResponseEntity.ok(groupsRepository.findAll());
    }

    public ResponseEntity<Object> xlsx(MultipartFile file) {
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            LinkedList<Groups> groupsLinkedList = new LinkedList<>();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                final Groups groups = new Groups();
                groups.setName(row.getCell(0).toString().trim());
                groupsLinkedList.add(groups);
            }
            groupsRepository.saveAll(groupsLinkedList);
            return new ResponseEntity<>(new ResponseResult(true, "SAqlandi"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseResult(false, "Saqlanmadi"), HttpStatus.BAD_REQUEST);
        }
    }
}
