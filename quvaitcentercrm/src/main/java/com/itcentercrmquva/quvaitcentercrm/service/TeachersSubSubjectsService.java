package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.dto.ResponseResult;
import com.itcentercrmquva.quvaitcentercrm.entity.OrganizationsSubjectsWithSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.entity.TeachersSubSubjects;
import com.itcentercrmquva.quvaitcentercrm.repository.PhysicalStuffRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TeachersSubSubjectsService {

    private final PhysicalStuffRepository physicalStuffRepository;


}
