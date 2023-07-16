package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/physical-face/")
public class PhysicalFaceController {

    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(@RequestParam("firstname") String firstname,
                                                 @RequestParam("lastname") String lastname,
                                                 @RequestParam("middlename") String middlename,
                                                 @RequestParam("birthday") LocalDate birthday,
                                                 @RequestParam("identificator") String identificator,
                                                 @RequestParam("address") String address,
                                                 @RequestParam("phone1") String primaryPhone,
                                                 @RequestParam("phone2") String secondaryPhone,
                                                 @RequestParam("telegram") String telegram,
                                                 @RequestParam("instagram") String instagram,
                                                 @RequestParam("e_level") Long id,
                                                 @RequestParam("interests") String interests){
        return  ResponseEntity.ok(new ResponseResult(false, "Not implemented yet"));
    }

}
