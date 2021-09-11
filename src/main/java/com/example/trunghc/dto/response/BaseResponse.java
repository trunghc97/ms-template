package com.example.trunghc.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {

    protected String refNo;

    protected Result result;
}
