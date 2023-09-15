package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.entity.Groups;
import com.itcentercrmquva.quvaitcentercrm.service.GroupsService;
import com.itcentercrmquva.quvaitcentercrm.service.SSCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupsController {

    private final GroupsService groupsService;

    @GetMapping("")
    public ResponseEntity<List<Groups>> getAll(){
        return groupsService.getAll();
    }

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestParam("name") String name) {
        return groupsService.create(name);
    }

    @PostMapping("upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {
        return groupsService.xlsx(file);
    }
}
