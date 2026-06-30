package com.sky.logistics.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginUserVO {

    private String id;
    private String username;
    private String name;
    private String role;
    private String phone;
    private List<String> permissions;
}
