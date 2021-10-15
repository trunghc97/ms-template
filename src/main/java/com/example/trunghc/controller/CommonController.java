package com.example.trunghc.controller;

import com.example.trunghc.dto.request.common.LoginRequest;
import com.example.trunghc.dto.request.common.RegisterRequest;
import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.dto.response.common.LoginResponse;
import com.example.trunghc.dto.response.common.RegisterResponse;
import com.example.trunghc.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> doRegister(@RequestBody RegisterRequest request) {
        return commonService.doRegister(request);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest request) {
        return commonService.doLogin(request);
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<BaseResponse> doLogout() {
        return commonService.doLogout();
    }

}
