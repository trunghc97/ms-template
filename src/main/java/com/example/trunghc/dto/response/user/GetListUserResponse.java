package com.example.trunghc.dto.response.user;

import com.example.trunghc.dto.response.BaseResponse;
import com.example.trunghc.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GetListUserResponse extends BaseResponse {
    private List<User> lstUser;
}
