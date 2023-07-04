package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.service.SubSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects/sub/")
public class SubSubjectController {

    private final SubSubjectService subSubjectService;

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, HttpServletRequest request) throws IOException {
        return subSubjectService.save(name, request);
    }

    @GetMapping("all")
    public ResponseEntity<Collection> getAllSubs(){
        return subSubjectService.getAllSubs();
    }

    @PostMapping("excel")
    public ResponseEntity<ResponseResult> readFromExcel(@RequestParam("excel") MultipartFile file, HttpServletRequest request){
       return subSubjectService.readAndSaveFromExcel(file, request);
    }

}

