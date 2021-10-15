package com.example.trunghc.dto.response.common;

import com.example.trunghc.dto.base.TokenDto;
import com.example.trunghc.dto.response.BaseResponse;
import lombok.Data;

@Data
public class LoginResponse extends BaseResponse {
    private TokenDto token;
}
