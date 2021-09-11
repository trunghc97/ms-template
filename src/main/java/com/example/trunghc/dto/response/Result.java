package com.example.trunghc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result implements Serializable {

    private String data;

    private boolean isOk;

    private String responseCode;

    public Result() {
        data = "SUCCESS";
        isOk = true;
        responseCode = "00";
    }

}
