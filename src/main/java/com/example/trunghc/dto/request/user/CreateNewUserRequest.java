package com.example.trunghc.dto.request.user;

import com.example.trunghc.dto.request.BaseRequest;
import lombok.Data;

@Data
public class CreateNewUserRequest extends BaseRequest {
    private String userId;

    private String userName;

    private String email;

    private String password;

    private String role;
}
