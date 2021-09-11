package com.example.trunghc.controller;

import com.example.trunghc.dto.request.LoginRequest;
import com.example.trunghc.dto.request.RegisterRequest;
import com.example.trunghc.dto.response.LoginResponse;
import com.example.trunghc.dto.response.RegisterResponse;
import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.service.CommonService;
import com.example.trunghc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return userService.createUser(request);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest request) {
        return commonService.doLogin(request);
    }

}
