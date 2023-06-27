package com.itcentercrmquva.quvaitcentercrm.controller.subjects;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.service.SubSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects/sub/")
public class SubSubjectController {

    private final SubSubjectService subSubjectService;

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, @RequestParam("subject_id") Long subjectId, @RequestParam("description") String description, HttpServletRequest request) {
        return subSubjectService.save(name, subjectId, description, request);
    }

    @GetMapping("all")
    public ResponseEntity<Collection> getAllSubs(){
        return subSubjectService.getAllSubs();
    }

}

