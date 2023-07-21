package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.EducationLevel;
import com.itcentercrmquva.quvaitcentercrm.service.EducationLeverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/education-level/")
@AllArgsConstructor
public class EducationLevelController {
    private final EducationLeverService educationLeverService;

    @GetMapping("all")
    public ResponseEntity<List<EducationLevel>> all() {
        return educationLeverService.getAllEducationLevels();
    }

    @PostMapping("upload")
    public ResponseEntity<ResponseResult> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return educationLeverService.uploadAllEducationLevels(file, request);
    }

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, HttpServletRequest request) {
        return educationLeverService.save(name, request);
    }
}

