package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.service.TeachersSubSubjectsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
