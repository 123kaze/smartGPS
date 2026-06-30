package com.sky.logistics.service;

import com.sky.logistics.dto.LoginDTO;
import com.sky.logistics.vo.LoginUserVO;
import com.sky.logistics.vo.LoginVO;

public interface AuthService {

    LoginVO login(LoginDTO loginDTO);

    LoginVO refresh(String refreshToken);

    LoginUserVO currentUser(String authorization);
}
