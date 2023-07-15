package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.StuffCategory;
import com.itcentercrmquva.quvaitcentercrm.service.StuffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/stuff/")
@AllArgsConstructor
public class StuffController {

    private final StuffService stuffService;
    @GetMapping("all")
    public ResponseEntity<List<StuffCategory>> allStuffs(){
        return stuffService.getAllStuff();
    }

    @PostMapping("upload")
    public ResponseEntity<ResponseResult> upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
      return stuffService.uploadAllStuffCategories(multipartFile, request);
    }
}
