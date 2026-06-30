package com.sky.logistics.controller;

import com.sky.logistics.common.ApiResponse;
import com.sky.logistics.dto.LoginDTO;
import com.sky.logistics.dto.RefreshTokenRequest;
import com.sky.logistics.service.AuthService;
import com.sky.logistics.vo.LoginUserVO;
import com.sky.logistics.vo.LoginVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public ApiResponse<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        return ApiResponse.success(authService.login(loginDTO));
    }

    @PostMapping("/auth/refresh")
    public ApiResponse<LoginVO> refresh(@RequestBody RefreshTokenRequest request) {
        return ApiResponse.success(authService.refresh(request == null ? null : request.getRefreshToken()));
    }

    @PostMapping("/auth/logout")
    public ApiResponse<Void> logout() {
        return ApiResponse.success();
    }

    @GetMapping("/users/me")
    public ApiResponse<LoginUserVO> me(@RequestHeader(value = "Authorization", required = false) String authorization) {
        return ApiResponse.success(authService.currentUser(authorization));
    }
}
