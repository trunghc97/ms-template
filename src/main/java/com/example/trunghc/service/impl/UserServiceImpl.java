package com.example.trunghc.service.impl;

import com.example.trunghc.dto.request.RegisterRequest;
import com.example.trunghc.dto.response.RegisterResponse;
import com.example.trunghc.dto.response.Result;
import com.example.trunghc.model.User;
import com.example.trunghc.repositories.UserRepository;
import com.example.trunghc.service.UserService;
import com.example.trunghc.utility.Utility;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utility utility;

    public UserServiceImpl(UserRepository userRepository, Utility utility) {
        this.userRepository = userRepository;
        this.utility = utility;
    }


    @Override
    public ResponseEntity<RegisterResponse> createUser(RegisterRequest request) {
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

}
