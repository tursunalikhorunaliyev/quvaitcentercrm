package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.LoginDTO;
import com.itcentercrmquva.quvaitcentercrm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.register(username, password);
    }

    @PostMapping("login")
    public ResponseEntity<LoginDTO> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}
