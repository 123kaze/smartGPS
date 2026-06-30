package com.sky.logistics.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {

    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
    private LoginUserVO user;
}
