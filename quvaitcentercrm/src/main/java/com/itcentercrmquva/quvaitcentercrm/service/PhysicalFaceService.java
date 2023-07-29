package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.*;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalFaceProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.EducationLevelRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.InterestsRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.PhysicalFaceRepository;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PhysicalFaceService {

    private final UserRepository userRepository;
    private final JWTGenerator jwtGenerator;

    private final PhysicalFaceRepository physicalFaceRepository;
    private final InterestsRepository interestsRepository;
    private final EducationLevelRepository educationLevelRepository;

    public ResponseEntity<ResponseResult> create(String firstname, String lastname, String middleName, String birthday,
                                                 String identification, String address, String primaryPhone, String secondaryPhone,
                                                 String telegram, String instagram, Long eLevelId, String interests, MultipartFile photo,
                                                 HttpServletRequest request) {


        Optional<Users> user = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Foydalanuvchi o'chirib yuborilgan"), HttpStatus.BAD_REQUEST);
        }

        if(physicalFaceRepository.existsByPersonalIdentification(identification.trim())){
            return new ResponseEntity<>(new ResponseResult(false, "Ushbu ma'lumot serverda mavjud"), HttpStatus.BAD_REQUEST);
        }

        firstname = firstname.trim();
        lastname = lastname.trim();
        identification = identification.trim();
        address = address.trim();
        primaryPhone = primaryPhone.trim();
        birthday = birthday.trim();
        String[] birthDayString = birthday.split("-");
        int[] birthdayInt = Arrays.stream(birthDayString)
                .mapToInt(Integer::parseInt)
                .toArray();
        ImageStore imageStore = new ImageStore();
        imageStore.setUsers(user.get());
        imageStore.setFilename(photo.getOriginalFilename());
        try {
            imageStore.setContent(photo.getBytes());
        } catch (IOException e) {
             return new ResponseEntity<>(new ResponseResult(false, "Rasm yuklashda muammo"), HttpStatus.BAD_REQUEST);
        }
        if (firstname.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Ism bo'sh"), HttpStatus.BAD_REQUEST);
        }
        if (lastname.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Familiya bo'sh"), HttpStatus.BAD_REQUEST);
        }
        if(birthday.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "Tug'ilgan sana kirtilmadi"), HttpStatus.BAD_REQUEST);
        }
        if (identification.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Passport seriyasi bo'sh"), HttpStatus.BAD_REQUEST);
        } else if (identification.length() != 9) {
            return new ResponseEntity<>(new ResponseResult(false, "Passport seriyasi to'g'ri kiritilmadi"), HttpStatus.BAD_REQUEST);
        }
        if (address.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Address bo'sh"), HttpStatus.BAD_REQUEST);
        }
        if (primaryPhone.isEmpty()) {
            return new ResponseEntity<>(new ResponseResult(false, "Telefon raqam kiritilmagan"), HttpStatus.BAD_REQUEST);
        } else if (primaryPhone.length() != 12) {
            return new ResponseEntity<>(new ResponseResult(false, "Asosiy telefon raqam xato kiritilgan"), HttpStatus.BAD_REQUEST);
        }
        Optional<EducationLevel> educationLevel = educationLevelRepository.findById(eLevelId);

        if(educationLevel.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "Educational level topilmadi"), HttpStatus.BAD_REQUEST);
        }

        PhysicalFace physicalFace = new PhysicalFace();
        physicalFace.setFirstName(firstname);
        physicalFace.setLastName(lastname);
        physicalFace.setAddress(address);
        physicalFace.setBirthday(LocalDate.of(birthdayInt[0], birthdayInt[1], birthdayInt[2]));
        physicalFace.setPersonalIdentification(identification);
        physicalFace.setPrimaryPhone(primaryPhone);
        physicalFace.setEducationLevel(educationLevel.get());
        physicalFace.setPhoto(imageStore);
        physicalFace.setUser(user.get());



        if (middleName != null) {
            physicalFace.setMiddleName(middleName.trim());
        }
        if (secondaryPhone != null) {
            physicalFace.setSecondaryPhone(secondaryPhone.trim());
        }
        if (telegram != null) {
            physicalFace.setTelegramUsername(telegram.trim());
        }
        if (instagram != null) {
            physicalFace.setInstagramUsername(instagram.trim());
        }
        if (interests != null) {
            String[] interestIds = interests.split(",");

          List<Long> ids =  Arrays.stream(interestIds).map(Long::valueOf).toList();


            Set<Interests> allInterests = interestsRepository.findInterestssByIds(ids);
            for(Interests a: allInterests){
                System.out.println("----------------------------------------------"+a.getName());
            }
            physicalFace.setInterests(allInterests);
            System.out.println("set bo'ldiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        }

        physicalFaceRepository.save(physicalFace);

        return ResponseEntity.ok(new ResponseResult(true, "Barcha ma'lumotlar saqlandi"));

    }

    public ResponseEntity<List<PhysicalFaceProjection>> getAll(HttpServletRequest request){
        Optional<Users> user = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        return ResponseEntity.ok(physicalFaceRepository.findAllFaces());

    }

    public ResponseEntity<Object> getById(Long id){
      Optional<PhysicalFaceProjection> physicalFaceProjection = physicalFaceRepository.findByIdByQuery(id);
        return physicalFaceProjection.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(new ResponseResult(false, "Ma'lumot topilmadi"), HttpStatus.BAD_REQUEST));
    }
}