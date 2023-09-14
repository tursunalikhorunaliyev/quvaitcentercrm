package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.service.SSCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ss-category")
@AllArgsConstructor
public class SSCategoryController {

    private final SSCategoryService ssCategoryService;

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestParam("name") String name) {
        return ssCategoryService.create(name);
    }

    @PostMapping("upload")
    public ResponseEntity<Object> create(@RequestParam("file") MultipartFile file) {
        return ssCategoryService.xlsx(file);
    }
}
