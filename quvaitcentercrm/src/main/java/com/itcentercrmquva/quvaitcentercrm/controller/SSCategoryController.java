package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.entity.SSCategory;
import com.itcentercrmquva.quvaitcentercrm.service.SSCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/ss-category")
@AllArgsConstructor
public class SSCategoryController {

    private final SSCategoryService ssCategoryService;

    @GetMapping("")
    public ResponseEntity<List<SSCategory>> getAll(){
        return ssCategoryService.getAll();
    }
    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestParam("name") String name) {
        return ssCategoryService.create(name);
    }

    @PostMapping("upload")
    public ResponseEntity<Object> create(@RequestParam("file") MultipartFile file) {
        return ssCategoryService.xlsx(file);
    }
}
