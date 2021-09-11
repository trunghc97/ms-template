package com.example.trunghc.dto.response.user;

import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.model.User;
import lombok.Data;

@Data
public class GetUserResponse extends BaseResponse {
    private User user;
}
