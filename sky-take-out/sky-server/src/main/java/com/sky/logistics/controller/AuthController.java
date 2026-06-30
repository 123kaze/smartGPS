package com.sky.logistics.controller;

import com.sky.logistics.common.ApiResponse;
import com.sky.logistics.dto.LoginRequest;
import com.sky.logistics.dto.RefreshTokenRequest;
import com.sky.logistics.service.LogisticsStarterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final LogisticsStarterService starterService;

    public AuthController(LogisticsStarterService starterService) {
        this.starterService = starterService;
    }

    @PostMapping("/auth/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Map<String, Object> data = starterService.login(request);
        if (data == null) {
            return ApiResponse.error(40101, "Invalid username or password", null);
        }
        return ApiResponse.success(data);
    }

    @PostMapping("/auth/refresh")
    public ApiResponse<Map<String, Object>> refresh(@RequestBody RefreshTokenRequest request) {
        return ApiResponse.success(starterService.login(defaultLogin()));
    }

    @PostMapping("/auth/logout")
    public ApiResponse<Void> logout() {
        return ApiResponse.success();
    }

    @GetMapping("/users/me")
    public ApiResponse<Map<String, Object>> me(@RequestHeader(value = "Authorization", required = false) String authorization) {
        return ApiResponse.success(starterService.currentUser(authorization));
    }

    private LoginRequest defaultLogin() {
        LoginRequest request = new LoginRequest();
        request.setUsername("dispatcher");
        request.setPassword("123456");
        return request;
    }
}
