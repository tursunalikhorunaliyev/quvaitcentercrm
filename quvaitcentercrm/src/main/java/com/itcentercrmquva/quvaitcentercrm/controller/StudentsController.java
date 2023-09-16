package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.StudentsProjection;
import com.itcentercrmquva.quvaitcentercrm.service.StudentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;

    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("middlename") String middlename, @RequestParam("phone") String phone, @RequestParam("tssId") Long tssId, HttpServletRequest request) {
        return studentsService.create(firstname, lastname, middlename, phone, tssId, request);
    }

    @GetMapping("")
    public ResponseEntity<StudentsProjection> getStudent(@RequestParam("id") Long id) {
        return studentsService.getStudent(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<StudentsProjection>> getAllStudent() {
        return studentsService.getAll();
    }
}
