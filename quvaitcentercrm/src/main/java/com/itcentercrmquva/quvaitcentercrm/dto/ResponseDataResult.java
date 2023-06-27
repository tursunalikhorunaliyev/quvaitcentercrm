package com.itcentercrmquva.quvaitcentercrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ResponseDataResult extends ResponseResult{


    private Object data;

    public ResponseDataResult(boolean status, String message, Object data) {
        super(status, message);
        this.data = data;
    }
}
