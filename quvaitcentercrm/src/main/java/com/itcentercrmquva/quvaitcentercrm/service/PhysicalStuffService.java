package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalFace;
import com.itcentercrmquva.quvaitcentercrm.entity.PhysicalStuff;
import com.itcentercrmquva.quvaitcentercrm.entity.StuffCategory;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffByCategoryProjection;
import com.itcentercrmquva.quvaitcentercrm.projection.PhysicalStuffProjection;
import com.itcentercrmquva.quvaitcentercrm.repository.*;
import com.itcentercrmquva.quvaitcentercrm.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhysicalStuffService {
    private final PhysicalStuffRepository physicalStuffRepository;
    private final JWTGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final StringToDateCheckerService dateService;
    private final PhysicalFaceRepository physicalFaceRepository;
    private final StuffCategoryRepository stuffCategoryRepository;

    public ResponseEntity<ResponseResult> create(Long fid, Long cid, String startDate, String endDate, HttpServletRequest request){
        Optional<Users> user = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));

        if(user.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "User topilmadi"), HttpStatus.BAD_REQUEST);
        }

        if(physicalStuffRepository.existsByPhysicalFace_IdAndStuffCategory_IdAndOrganizations_Id(fid, cid, user.get().getOrganization().getId())){
            return new ResponseEntity<>(new ResponseResult(false, "Ushbu ma'lumot oldin yaratilgan"), HttpStatus.BAD_REQUEST);
        }
        Optional<PhysicalFace> faceOptional = physicalFaceRepository.findById(fid);
        if(faceOptional.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "Jismoniy shahs topilmadi"), HttpStatus.BAD_REQUEST);
        }
        Optional<StuffCategory> stuffCategoryOptional = stuffCategoryRepository.findById(cid);
        if(stuffCategoryOptional.isEmpty()){
            return new ResponseEntity<>(new ResponseResult(false, "Kategoriya topilmadi"), HttpStatus.BAD_REQUEST);
        }
        PhysicalStuff physicalStuff = new PhysicalStuff();
        if(dateService.isDateAvailable(startDate.trim())){
            return new ResponseEntity<>(new ResponseResult(false, "Start date xato kiritildi"), HttpStatus.BAD_REQUEST);
        }
        if(endDate!=null){
            if(dateService.isDateAvailable(endDate.trim())){
                return new ResponseEntity<>(new ResponseResult(false, "End date xato kiritildi"), HttpStatus.BAD_REQUEST);
            }
            physicalStuff.setEndDate(dateService.generateLocalDate(endDate.trim()));

        }

        physicalStuff.setPhysicalFace(faceOptional.get());
        physicalStuff.setStuffCategory(stuffCategoryOptional.get());
        physicalStuff.setStartDate(dateService.generateLocalDate(startDate.trim()));
        physicalStuff.setOrganizations(user.get().getOrganization());
        physicalStuff.setUser(user.get());
        physicalStuffRepository.save(physicalStuff);

        return ResponseEntity.ok(new ResponseResult(true, "Ma'lumotlar saqlandi"));
    }

     public ResponseEntity<List<PhysicalStuffProjection>> getAllByOrg(HttpServletRequest request){
        Optional<Users> usersOptional = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        return ResponseEntity.ok(physicalStuffRepository.findByOrganizations(usersOptional.get().getOrganization()));
     }

     public ResponseEntity<List<PhysicalStuffProjection>> getAllByOrgAndStuff(HttpServletRequest request, Long cid){
         Optional<Users> usersOptional = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
         return ResponseEntity.ok(physicalStuffRepository.findByOrganizationsAndStuffCategory_Id(usersOptional.get().getOrganization(), cid));
     }
     public ResponseEntity<List<PhysicalStuffByCategoryProjection>> getPSByCategory(HttpServletRequest request){
         Optional<Users> usersOptional = userRepository.findByUsername(jwtGenerator.getUsernameFromToken(request.getHeader("Authorization").substring(7)));
        return  ResponseEntity.ok(physicalStuffRepository.getPSbyCategory(usersOptional.get().getOrganization()));
     }
}
