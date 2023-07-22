package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffByCategoryProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffProjection;
import com.itcentercrmquva.quvaitcentercrm.service.PhysicalStuffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/physical-stuff/")
@AllArgsConstructor
public class PhysicalStuffController {

    private final PhysicalStuffService physicalStuffService;

    @PostMapping("create")
    public ResponseEntity<ResponseResult> create(
            @RequestParam("fid") Long fid,
            @RequestParam("cid") Long cid,
            @RequestParam("start_date") String startDate,
            @RequestParam(value = "end_date", required = false) String endDate,
            HttpServletRequest request){
        return physicalStuffService.create(fid, cid, startDate, endDate, request);
    }

    @GetMapping("all")
    public ResponseEntity<List<PhysicalStuffProjection>> getAll(HttpServletRequest request){
        return physicalStuffService.getAllByOrg(request);
    }

    @GetMapping("by-category")
    public ResponseEntity<List<PhysicalStuffProjection>> getAllByCategory(@RequestParam("cid") Long cid, HttpServletRequest request){
        return physicalStuffService.getAllByOrgAndStuff(request, cid);
    }

    @GetMapping("categories")
    public ResponseEntity<List<PhysicalStuffByCategoryProjection>> getAllPSByCategory(HttpServletRequest request){
        return physicalStuffService.getPSByCategory(request);
    }



}
