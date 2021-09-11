package com.example.trunghc.service.impl;

import com.example.trunghc.dto.request.LoginRequest;
import com.example.trunghc.dto.response.LoginResponse;
import com.example.trunghc.dto.response.Result;
import com.example.trunghc.dto.sercurity.UserPrincipal;
import com.example.trunghc.model.Token;
import com.example.trunghc.model.User;
import com.example.trunghc.repositories.TokenRepository;
import com.example.trunghc.repositories.UserRepository;
import com.example.trunghc.service.CommonService;
import com.example.trunghc.utility.JwtUtility;
import com.example.trunghc.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            userPrincipal.setUserId(user.getUserId());
            userPrincipal.setUsername(user.getName());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(List.of(user.getRole()));

            Token token = new Token();
            token.setToken(jwtUtility.generateToken(userPrincipal));
            token.setTokenExpDate(jwtUtility.generateExpirationDate());
            token.setCreatedBy(userPrincipal.getUserId());
            tokenRepository.save(token);

            response.setResult(result);
            response.setToken(token);
        } catch (Exception e) {
            result = new Result(e.getMessage(), false, "000");
            response.setResult(result);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
