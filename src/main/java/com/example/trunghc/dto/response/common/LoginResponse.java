package com.example.trunghc.dto.response.common;

import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.model.Token;
import lombok.Data;

@Data
public class LoginResponse extends BaseResponse {
    private Token token;
}
