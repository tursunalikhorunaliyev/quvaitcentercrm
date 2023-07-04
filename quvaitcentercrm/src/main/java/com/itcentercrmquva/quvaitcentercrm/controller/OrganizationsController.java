package com.itcentercrmquva.quvaitcentercrm.controller;
import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.projection.projections.OrganizationProjection;
import com.itcentercrmquva.quvaitcentercrm.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/org/")
@AllArgsConstructor
public class OrganizationsController {

    private final OrganizationService organizationService;
    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(@RequestParam("org") String orgName, @RequestParam("address") String address, @RequestParam("phone1") String phone1, @RequestParam(value = "phone2", required = false) String phone2, @RequestParam(value = "email", required = false) String email, @RequestParam("INN") String inn, @RequestParam("gNumber") String gNumber, @RequestParam("gPhoto") MultipartFile gPhoto, HttpServletRequest httpRequest) {
        return organizationService.create(orgName, address, phone1, phone2, email, inn, gNumber, gPhoto, httpRequest);
    }
    @GetMapping("all")
    public  ResponseEntity<List<OrganizationProjection>> getAll(){
        return organizationService.allOrg();
    }
}
