package com.example.trunghc.dto.request;


import lombok.*;

@Data
public class LoginRequest extends BaseRequest {

    private String userId;

    private String password;

}
