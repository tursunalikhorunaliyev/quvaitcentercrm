package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.OnlyTeacherSubSubjectProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersSubSubjectsProjection;
import com.itcentercrmquva.quvaitcentercrm.service.TeachersSubSubjectsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/teachers/")
@AllArgsConstructor
public class TeachersSubSubjectsController {

    private final TeachersSubSubjectsService teachersSubSubjectsService;

    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(@RequestParam("physical_stuff") Long physicalStuffId, @RequestParam("org_ss") String ids, HttpServletRequest request) {
        return teachersSubSubjectsService.create(physicalStuffId, ids, request);
    }

    @GetMapping("")
    public ResponseEntity<List<TeachersSubSubjectsProjection>> list(HttpServletRequest request) {
        return teachersSubSubjectsService.getList(request);
    }

    @GetMapping("get")
    public ResponseEntity<OnlyTeacherSubSubjectProjection> get(@RequestParam("id") Long id) {
        return teachersSubSubjectsService.get(id);
    }
}
