package com.example.trunghc.service.impl;

import com.example.trunghc.dto.base.TokenDto;
import com.example.trunghc.dto.request.common.LoginRequest;
import com.example.trunghc.dto.request.common.RegisterRequest;
import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.dto.response.Result;
import com.example.trunghc.dto.response.common.LoginResponse;
import com.example.trunghc.dto.response.common.RegisterResponse;
import com.example.trunghc.dto.sercurity.UserPrincipal;
import com.example.trunghc.entity.Token;
import com.example.trunghc.entity.User;
import com.example.trunghc.repositories.TokenRepository;
import com.example.trunghc.repositories.UserRepository;
import com.example.trunghc.service.CommonService;
import com.example.trunghc.utility.JwtUtility;
import com.example.trunghc.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtUtility jwtUtility;
    private final Utility utility;

    public CommonServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, JwtUtility jwtUtility, Utility utility) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtUtility = jwtUtility;
        this.utility = utility;
    }

    @Override
    public ResponseEntity<LoginResponse> doLogin(LoginRequest request) {
        Result result = new Result();
        LoginResponse response = new LoginResponse();

        try {
            User user = userRepository.findFirstByUserId(request.getUserId());
            if (user == null || !utility.checkPassword(request.getPassword(), user.getPassword())) {
                result = new Result("Wrong UserId and Password", false, "001");
                response.setResult(result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setId(user.getId());
            userPrincipal.setUserId(user.getUserId());
            userPrincipal.setUsername(user.getName());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(List.of(user.getRole()));

            Token token = tokenRepository.findByUserIdAndDeleted(user.getId(), false);

            if (token == null) {
                token = new Token();
                token.setCreatedBy(String.valueOf(userPrincipal.getId()));
                token.setUserId(userPrincipal.getId());
            }
            token.setToken(jwtUtility.generateToken(userPrincipal));
            token.setTokenExpDate(jwtUtility.generateExpirationDate());
            tokenRepository.save(token);

            TokenDto tokenDto = new TokenDto(token);

            response.setResult(result);
            response.setToken(tokenDto);
        } catch (Exception e) {
            result = new Result(e.getMessage(), false, "000");
            response.setResult(result);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RegisterResponse> doRegister(RegisterRequest request) {
        Result result = new Result();
        RegisterResponse response = new RegisterResponse();

        try {
            User user = new User(request);
            user.setPassword(utility.generatePassword(request.getPassword()));
            userRepository.save(user);

            response.setUser(user);
        } catch (Exception e) {
            result = new Result(e.getMessage(), false, "000");
        }

        response.setResult(result);
        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<BaseResponse> doLogout() {
        Result result = new Result();
        BaseResponse response = new BaseResponse();

        try {
            String strToken = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

            Token token = tokenRepository.findByToken(strToken);
            token.setDeleted(true);
            tokenRepository.saveAndFlush(token);
        } catch (Exception e) {
            result = new Result(e.getMessage(), false, "000");
        }

        response.setResult(result);
        return ResponseEntity.ok(response);
    }

}
