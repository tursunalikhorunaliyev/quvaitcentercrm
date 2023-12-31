package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.LoginDTO;
import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.Roles;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.OrganizationsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.RoleRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTAuthenticationFilter;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;


    private OrganizationsRepository organizationsRepository;


    public ResponseEntity<String> register(String username, String password, Long oId) {
        username = username.trim();
        password = password.trim();


        if(password.length()<8){
            return  new ResponseEntity<>("Password should be at least 8 character", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUsername(username)){
            return new  ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }



        Users users = new Users();

        /*Optional<Organizations> organizations = organizationsRepository.findById(oId);
        if (organizations.isPresent()) {
            users.setOrganization(organizations.get());
        } else {
            return new ResponseEntity<>("Organization not found", HttpStatus.BAD_REQUEST);
        }*/

        users.setUsername(username);
        users.setPassword(passwordEncoder.encode(password));



        Roles role = roleRepository.findByName("USER").get();
        users.setRoles(Collections.singleton(role));

        userRepository.save(users);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    public ResponseEntity<LoginDTO> login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new LoginDTO("User successfuly logged in",token), HttpStatus.OK);
    }

    public ResponseEntity<List<Long>> getUserIDs(HttpServletRequest request) {
        Optional<Users> user = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        if(user.isPresent()){
            final List<Users> users = userRepository.findAllByRoles_NameAndOrganization_Id("USER", user.get().getOrganization().getId());
            List<Long> ids = users.stream().map(Users::getId).toList();

            return new ResponseEntity<>(ids, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<Long> getUserID(HttpServletRequest request) {
        Optional<Users> user = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        if(user.isPresent()){
            final Optional<Users> users = userRepository.findByRoles_NameAndOrganization_Id("USER", user.get().getOrganization().getId());
            return new ResponseEntity<Long>(users.get().getId(), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}

