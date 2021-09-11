package com.example.trunghc.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest extends BaseRequest {
    private String userId;

    private String userName;

    private String email;

    private String password;
}
