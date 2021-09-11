package com.example.trunghc.dto.response;

import com.example.trunghc.model.User;
import lombok.Data;

@Data
public class RegisterResponse extends BaseResponse {
    private User user;
}
