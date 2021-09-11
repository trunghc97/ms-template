package com.example.trunghc.service.impl;

import com.example.trunghc.dto.request.user.CreateNewUserRequest;
import com.example.trunghc.dto.response.Result;
import com.example.trunghc.dto.response.user.GetListUserResponse;
import com.example.trunghc.dto.response.user.GetUserResponse;
import com.example.trunghc.model.User;
import com.example.trunghc.repositories.UserRepository;
import com.example.trunghc.service.UserService;
import com.example.trunghc.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utility utility;

    public UserServiceImpl(UserRepository userRepository, Utility utility) {
        this.userRepository = userRepository;
        this.utility = utility;
    }


    @Override
    public ResponseEntity<GetListUserResponse> getListUser() {
        Result result = new Result();
        GetListUserResponse response = new GetListUserResponse();

        try {
            List<User> lstUser = userRepository.findAll();
            response.setLstUser(lstUser);
        } catch (Exception e) {
            result = new Result(e.getMessage(), false, "000");
        }

        response.setResult(result);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetUserResponse> createUser(CreateNewUserRequest request) {
        Result result = new Result();
        GetUserResponse response = new GetUserResponse();

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
    public ResponseEntity<GetUserResponse> getUser(Integer id) {
        Result result = new Result();
        GetUserResponse response = new GetUserResponse();

        try {
            Optional<User> user = userRepository.findById(Long.parseLong(String.valueOf(id)));

            if (user.isPresent()) {
                response.setUser(user.get());
            } else {
                result = new Result("Can't find user", true, "000");
                response.setResult(result);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            result = new Result(e.getMessage(), false, "000");
        }

        response.setResult(result);
        return ResponseEntity.ok(response);
    }

}
