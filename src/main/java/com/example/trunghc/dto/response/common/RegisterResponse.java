package com.example.trunghc.dto.response.common;

import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.model.User;
import lombok.Data;

@Data
public class RegisterResponse extends BaseResponse {
    private User user;
}
