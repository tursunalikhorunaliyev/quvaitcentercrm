package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersSubSubjectProjection;
import com.itcentercrmquva.quvaitcentercrm.service.TeachersSubSubjectsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/teachers-ss/")
@AllArgsConstructor
public class TeachersSubSubjectsController {

    private final TeachersSubSubjectsService teachersSubSubjectsService;

    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(@RequestParam("physical_stuff") Long physicalStuffId, @RequestParam("org_ss") String ids, HttpServletRequest request) {
        return teachersSubSubjectsService.create(physicalStuffId, ids, request);
    }

    @GetMapping("get")
    public ResponseEntity<TeachersSubSubjectProjection> create(@RequestParam("id") Long id) {
        return teachersSubSubjectsService.getTeacherSubSubject(id);
    }
}
