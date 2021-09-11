package com.example.trunghc.dto.response;

import com.example.trunghc.model.Token;
import lombok.Data;

@Data
public class LoginResponse extends BaseResponse {
    private Token token;
}
