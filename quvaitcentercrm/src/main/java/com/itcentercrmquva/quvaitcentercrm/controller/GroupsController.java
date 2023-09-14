package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.service.GroupsService;
import com.itcentercrmquva.quvaitcentercrm.service.SSCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupsController {

    private final GroupsService groupsService;

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestParam("name") String name) {
        return groupsService.create(name);
    }

    @PostMapping("upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {
        return groupsService.xlsx(file);
    }
}
