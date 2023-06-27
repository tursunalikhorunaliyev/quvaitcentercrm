package com.itcentercrmquva.quvaitcentercrm.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String message;
    private String token;

    public LoginDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
