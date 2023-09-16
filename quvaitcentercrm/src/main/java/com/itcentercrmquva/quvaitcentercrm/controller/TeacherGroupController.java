package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersGroupProjection;
import com.itcentercrmquva.quvaitcentercrm.service.TeacherGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/teacher-group")
@AllArgsConstructor
public class TeacherGroupController {

    public final TeacherGroupService teacherGroupService;

    @GetMapping
    public ResponseEntity<TeachersGroupProjection> getTG(@RequestParam("id") Long id) {
        return teacherGroupService.getTeacherGroup(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeachersGroupProjection>> getAllTG() {
        return teacherGroupService.getAllTeacherGroup();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestParam("group_id") Long gId, @RequestParam("group_status_id") Long gsId, @RequestParam("ss_category_id") Long sscId, @RequestParam("teacher_subsubject_id") Long tssId, HttpServletRequest request) {
        return teacherGroupService.create(gId, gsId, sscId, tssId, request);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseResult> create(@RequestParam("description") String description, @RequestParam("id") Long id, @RequestParam("group_id") Long gId, @RequestParam("group_status_id") Long gsId, @RequestParam("ss_category_id") Long sscId, @RequestParam("teacher_subsubject_id") Long tssId, HttpServletRequest request) {
        return teacherGroupService.update(description, id, gId, gsId, sscId, tssId, request);
    }
}
