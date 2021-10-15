package com.example.trunghc.dto.base;

import com.example.trunghc.entity.Token;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenDto implements Serializable {
    private String token;

    private Date tokenExpDate;

    private Long userId;

    public TokenDto(Token token) {
        this.token = token.getToken();
        this.tokenExpDate = token.getTokenExpDate();
        this.userId = token.getUserId();
    }
}
