package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Interests;
import com.itcentercrmquva.quvaitcentercrm.service.InterestsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/interests/")
@AllArgsConstructor
public class InterestController {
    private final InterestsService interestsService;

    @GetMapping("api")
    public ResponseEntity<List<Interests>> all() {
        return interestsService.getAllInterests();
    }

    @PostMapping("upload")
    public ResponseEntity<ResponseResult> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return interestsService.uploadAllInterests(file, request);
    }

    @PostMapping("save")
    public ResponseEntity<ResponseResult> save(@RequestParam("name") String name, HttpServletRequest request) {
        return interestsService.save(name, request);
    }
}
