package com.itcentercrmquva.quvaitcentercrm.controller;

import com.itcentercrmquva.quvaitcentercrm.dto.LoginDTO;
import com.itcentercrmquva.quvaitcentercrm.entity.Roles;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.RoleRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestParam("username") String username, @RequestParam("password") String password){

        username = username.trim();
        password = password.trim();
        if(password.length()<8){
            return  new ResponseEntity<>("Password should be at least 8 character", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUsername(username)){
            return new  ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }

        Users users = new Users();
        users.setUsername(username);
        users.setPassword(passwordEncoder.encode(password));

        Roles role = roleRepository.findByName("USER").get();
        users.setRoles(Collections.singleton(role));
        userRepository.save(users);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
    @PostMapping("login")
    public ResponseEntity<LoginDTO> login(@RequestParam("username") String username, @RequestParam("password") String password){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return  new ResponseEntity<>(new LoginDTO("User successfuly logged in",token), HttpStatus.OK);
    }
}
