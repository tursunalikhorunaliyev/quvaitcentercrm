package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseDataResult;
import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.ImageStore;
import com.itcentercrmquva.quvaitcentercrm.entity.Organizations;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.projection.projections.OrganizationProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.OrganizationsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.RoleRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationsRepository organizationsRepository;
    private final JWTGenerator jwtGenerator;
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public ResponseEntity<ResponseResult> create(String orgName, String address, String phone1, String phone2, String email, String inn, String gNumber, MultipartFile gPhoto, HttpServletRequest httpRequest) {

        if(organizationsRepository.existsBygNumber(gNumber.trim())){
            return new ResponseEntity<>(new ResponseResult(false, "Organizatsiya oldin yaratilgan"), HttpStatus.BAD_REQUEST);
        }

        String username = jwtGenerator.getUsernameFromToken(httpRequest.getHeader("Authorization").substring(7));
        Optional<Users> users = userRepository.findByUsername(username);
        if(users.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "User topilmadi"), HttpStatus.BAD_REQUEST);
        }
       if(!users.get().getRoles().contains(roleRepository.findByName("SUPERADMIN").get())){
           return  new ResponseEntity<>(new ResponseResult(false, "Organizatsiya qo'shish ushbu user uchun cheklangan"), HttpStatus.BAD_REQUEST);
       }
       if(phone1.trim().length()!=12 || !phone1.trim().startsWith("998")){
         return  new ResponseEntity<>(new ResponseResult(false, "Telefon raqam xato kiriilgan"), HttpStatus.BAD_REQUEST);
       }
       if(email!=null){
           if(!email.contains("gmail.com")){
               return  new ResponseEntity<>(new ResponseResult(false, "Email ni tog'ri kiriting"), HttpStatus.BAD_REQUEST);
           }

       }
       if(inn.trim().length()!=9){
           return  new ResponseEntity<>(new ResponseResult(false, "INN xato kiritilgan"), HttpStatus.BAD_REQUEST);
       }
       if(gPhoto==null){
           return new ResponseEntity<>(new ResponseResult(false, "Guvohnoma rasmi ko'rsatilmagan"), HttpStatus.BAD_REQUEST);
       }

       ImageStore imageStore = new ImageStore();
        try {
            imageStore.setContent(gPhoto.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseResult(false, "Guvohnoma rasmi yuklashda xatolik"), HttpStatus.BAD_REQUEST);
        }
        imageStore.setFilename(gPhoto.getOriginalFilename());


       Organizations organizations = new Organizations();
       organizations.setName(orgName.trim());
       organizations.setAddress(address.trim());
       organizations.setTel1(phone1.trim());
       if(phone2!=null){
           organizations.setTel2(phone2.trim());
       }
       if(email!=null){

           organizations.setEmail(email.trim());
       }
       organizations.setINN(inn.trim());
       organizations.setGNumber(gNumber.trim());
       organizations.setGPhoto(imageStore);

       organizationsRepository.save(organizations);

       return new ResponseEntity<>(new ResponseResult(true, "Organizatsiya muvaffaqiyatli yaratildi"), HttpStatus.OK);
    }

    public ResponseEntity<List<OrganizationProjection>> allOrg(){
        return new ResponseEntity<>(organizationsRepository.getAllOrg(), HttpStatus.OK);

    }
}
