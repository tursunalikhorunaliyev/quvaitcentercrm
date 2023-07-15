package com.itcentercrmquva.quvaitcentercrm.controller;
import com.itcentercrmquva.quvaitcentercrm.dto.OrganizationSubjects;
import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.OrganizationsSSProjection;
import com.itcentercrmquva.quvaitcentercrm.service.OrganizationsSSService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/org/ss")
@AllArgsConstructor
public class OrganizationsSSController {


    private final OrganizationsSSService organizationsSSService;

    @PostMapping("create")
    public ResponseEntity<ResponseResult> createSS(@RequestParam("sid") Long sid, @RequestParam("ssid") Long ssid, @RequestParam("description") String description, @RequestParam("photo") MultipartFile image, HttpServletRequest request) {
        return organizationsSSService.createSS(sid, ssid, description, image, request);
    }
    @GetMapping("all-by-org")
    public ResponseEntity<List<OrganizationsSSProjection>> getAllOrgSS(HttpServletRequest request) {
        return organizationsSSService.getAllOrganizationsSS(request);
    }
    @GetMapping("org-subjects")
    public ResponseEntity<Set<OrganizationSubjects>> getAllOrgSubjects(HttpServletRequest request){
        return organizationsSSService.getOrgSubjects(request);
    }
    @GetMapping("ss-by-org")
    public ResponseEntity<List<OrganizationsSSProjection>> getSSById(@RequestParam Long sid, HttpServletRequest request) {
        return organizationsSSService.getAllOrganizationSSBySubject(request, sid);
    }
}
