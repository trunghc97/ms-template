package com.example.trunghc.service;

import com.example.trunghc.dto.request.user.CreateNewUserRequest;
import com.example.trunghc.dto.response.user.GetListUserResponse;
import com.example.trunghc.dto.response.user.GetUserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<GetListUserResponse> getListUser();

    ResponseEntity<GetUserResponse> createUser(CreateNewUserRequest request);

    ResponseEntity<GetUserResponse> getUser(Integer id);
}
