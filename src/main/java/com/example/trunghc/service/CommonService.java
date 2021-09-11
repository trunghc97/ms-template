package com.example.trunghc.service;

import com.example.trunghc.dto.request.common.LoginRequest;
import com.example.trunghc.dto.request.common.RegisterRequest;
import com.example.trunghc.dto.response.common.LoginResponse;
import com.example.trunghc.dto.response.common.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface CommonService {
    ResponseEntity<LoginResponse> doLogin(LoginRequest request);

    ResponseEntity<RegisterResponse> doRegister(RegisterRequest request);

}
