package com.example.trunghc.dto.request.common;


import com.example.trunghc.dto.request.BaseRequest;
import lombok.*;

@Data
public class LoginRequest extends BaseRequest {

    private String userId;

    private String password;

}
