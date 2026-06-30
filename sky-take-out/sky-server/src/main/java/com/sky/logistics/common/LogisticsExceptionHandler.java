package com.sky.logistics.common;

import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.sky.logistics")
@Slf4j
public class LogisticsExceptionHandler {

    @ExceptionHandler(LogisticsAuthException.class)
    public ApiResponse<Map<String, Object>> handleAuth(LogisticsAuthException exception) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("reason", exception.getMessage());
        return ApiResponse.error(40101, exception.getMessage(), data);
    }

    @ExceptionHandler({IllegalArgumentException.class, JwtException.class})
    public ApiResponse<Map<String, Object>> handleBadRequest(Exception exception) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("reason", exception.getMessage());
        return ApiResponse.error(40001, exception.getMessage(), data);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Map<String, Object>> handle(Exception exception) {
        log.error("logistics server error", exception);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("reason", exception.getMessage());
        return ApiResponse.error(50001, "Server error", data);
    }
}
