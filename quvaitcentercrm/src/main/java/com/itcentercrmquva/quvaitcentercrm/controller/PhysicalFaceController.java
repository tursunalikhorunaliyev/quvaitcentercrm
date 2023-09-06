package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalFaceProjection;
import com.itcentercrmquva.quvaitcentercrm.service.PhysicalFaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/physical-face/")
@AllArgsConstructor
public class PhysicalFaceController {

    private final PhysicalFaceService physicalFaceService;


    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(@RequestParam("firstname") String firstname,
                                                 @RequestParam("lastname") String lastname,
                                                 @RequestParam(value = "middleName", required = false) String middleName,
                                                 @RequestParam("birthday") String birthday,
                                                 @RequestParam("identification") String identification,
                                                 @RequestParam("address") String address,
                                                 @RequestParam("phone1") String primaryPhone,
                                                 @RequestParam(value = "phone2", required = false) String secondaryPhone,
                                                 @RequestParam(value = "telegram", required = false) String telegram,
                                                 @RequestParam(value = "instagram", required = false) String instagram,
                                                 @RequestParam("e_level") Long eLevelId,
                                                 @RequestParam(value = "interests", required = false) String interests,
                                                 @RequestParam("photo") MultipartFile photo,
                                                 HttpServletRequest request) {
        System.out.println(birthday);
        return physicalFaceService.create(firstname, lastname, middleName, birthday, identification, address, primaryPhone, secondaryPhone, telegram, instagram, eLevelId, interests, photo, request);
    }

    @PutMapping("update")
    public ResponseEntity<Boolean> create(@RequestParam("id") Long id,
                                                 @RequestParam(value = "firstname", required = false) String firstname,
                                                 @RequestParam(value = "lastname", required = false) String lastname,
                                                 @RequestParam(value = "middleName", required = false) String middleName,
                                                 @RequestParam(value = "birthday", required = false) String birthday,
                                                 @RequestParam(value = "identification", required = false) String identification,
                                                 @RequestParam(value = "address", required = false) String address,
                                                 @RequestParam(value = "phone1", required = false) String primaryPhone,
                                                 @RequestParam(value = "phone2", required = false) String secondaryPhone,
                                                 @RequestParam(value = "telegram", required = false) String telegram,
                                                 @RequestParam(value = "instagram", required = false) String instagram,
                                                 @RequestParam(value = "e_level", required = false) Long eLevelId,
                                                 @RequestParam(value = "interests", required = false) String interests,
                                                 @RequestParam(value = "photo", required = false) MultipartFile photo,
                                          HttpServletRequest httpServletRequest
                                          ) {
        return physicalFaceService.updatePhysicalFace(id, firstname, lastname, middleName, birthday, identification, address, primaryPhone, secondaryPhone, telegram, instagram, eLevelId, interests, photo, httpServletRequest);
    }

    @GetMapping("all")
    public ResponseEntity<List<PhysicalFaceProjection>> getAll(HttpServletRequest request) {
        return physicalFaceService.getAll(request);
    }

    @GetMapping("single")
    public ResponseEntity<Object> getById(@RequestParam("pId") Long id) {
        return physicalFaceService.getById(id);
    }

}
