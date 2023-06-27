package com.itcentercrmquva.quvaitcentercrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult {
    private boolean status;
    private String message;
}
