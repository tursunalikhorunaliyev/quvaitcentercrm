package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.entity.GroupStatus;
import com.itcentercrmquva.quvaitcentercrm.repository.GroupStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/group-status")
@AllArgsConstructor
public class GroupStatusController {

    private final GroupStatusRepository groupStatusRepository;
    @GetMapping("")
    public ResponseEntity<List<GroupStatus>> get(){
        return ResponseEntity.ok(groupStatusRepository.findAll());
    }
    
    @PostMapping("create")
    public ResponseEntity<Boolean> create(@RequestParam("name") String name){
        GroupStatus groupStatus = new GroupStatus();
        groupStatus.setName(name);
        groupStatusRepository.save(groupStatus);
        return ResponseEntity.ok(true);
    }
}
