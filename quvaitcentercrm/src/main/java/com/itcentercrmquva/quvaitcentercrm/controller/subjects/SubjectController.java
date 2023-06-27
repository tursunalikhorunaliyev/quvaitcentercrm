package com.itcentercrmquva.quvaitcentercrm.controller.subjects;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, HttpServletRequest request) {
        return subjectService.save(name, request);
    }

    @GetMapping("all")
    public ResponseEntity<Collection> getAllSubjects(){
        return subjectService.getAllSubject();
    }
}
