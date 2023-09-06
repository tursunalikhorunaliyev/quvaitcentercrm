package com.itcentercrmquva.quvaitcentercrm.controller;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.itcentercrmquva.quvaitcentercrm.dto.LoginDTO;
import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("org_id") Long oId) {
        return userService.register(username, password, oId);
    }

    @PostMapping("login")
    public ResponseEntity<LoginDTO> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
    @PostMapping("silentNotification")
    public ResponseEntity<ResponseResult> notification() throws IOException {
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream(new File(getClass().getResource("jsonschema.json").getFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        Message message = Message.builder().setToken("classpath:cbWo_Zt9Tbe_iWiaXLMExG:APA91bG7GPU4IUiwIlvKVCTPo6EXpeXl5LodPV_1NORp3mgQvZIGtYprChkAYrlgiImPTsav6aE3EQxRdVtL1bvqAZDDW0w6QBs9-myO-HZGpClmIsHtBsw6mFpC9B0uYYL5UsW9AJ8o").putData("silent","true").build();
        return ResponseEntity.ok(new ResponseResult(true,"Silent message is sendded"));
    }
}
