package com.itcentercrmquva.quvaitcentercrm.controller;


import com.itcentercrmquva.quvaitcentercrm.repository.ImageStoreRepository;
import com.itcentercrmquva.quvaitcentercrm.service.ImageStoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image/")
@AllArgsConstructor
public class ImageController {

    private final ImageStoreService imageStoreService;
    @GetMapping("get")
    public ResponseEntity<String> getImage(@RequestParam Long id){
        return imageStoreService.getImage(id);
    }
}
