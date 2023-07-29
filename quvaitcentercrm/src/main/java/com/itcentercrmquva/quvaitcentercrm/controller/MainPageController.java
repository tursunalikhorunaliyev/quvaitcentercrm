package com.itcentercrmquva.quvaitcentercrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainPageController {
    @GetMapping("/netflex")
    public String home(){
        return "index";
        //kkk
    }
}
