package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.service.SubjectService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, HttpServletRequest request) {
        return subjectService.save(name, request);
    }

    @GetMapping("all")
    public ResponseEntity<Collection> getAllSubjects() {
        return subjectService.getAllSubject();
    }

    @PostMapping("excel")
    public ResponseEntity<ResponseResult> readFromExcel(@RequestParam("excel") MultipartFile file) {
        return subjectService.readAndSaveFromExcel(file);
    }
}
