package com.example.trunghc.service;

import com.example.trunghc.dto.request.RegisterRequest;
import com.example.trunghc.dto.response.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<RegisterResponse> createUser(RegisterRequest request);
}
