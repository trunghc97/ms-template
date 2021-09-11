package com.example.trunghc.service;

import com.example.trunghc.dto.request.LoginRequest;
import com.example.trunghc.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface CommonService {
    ResponseEntity<LoginResponse> doLogin(LoginRequest request);
}
