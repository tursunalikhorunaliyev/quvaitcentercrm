package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.TeachersGroupProjection;
import com.itcentercrmquva.quvaitcentercrm.service.TeacherGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/teacher-group/")
@AllArgsConstructor
public class TeacherGroupController {

    public final TeacherGroupService teacherGroupService;

    public ResponseEntity<TeachersGroupProjection> getTG(@RequestParam("id") Long id) {
        return teacherGroupService.getTeacherGroup(id);
    }

    public ResponseEntity<List<TeachersGroupProjection>> getAllTG() {
        return teacherGroupService.getAllTeacherGroup();
    }

    public ResponseEntity<ResponseResult> create(@RequestParam("group_id") Long gId, @RequestParam("group_status_id") Long gsId, @RequestParam("ss_category_id") Long sscId, @RequestParam("teacher_subsubject_id") Long tssId, HttpServletRequest request) {
        return teacherGroupService.create(gId, gsId, sscId, tssId, request);
    }
}
