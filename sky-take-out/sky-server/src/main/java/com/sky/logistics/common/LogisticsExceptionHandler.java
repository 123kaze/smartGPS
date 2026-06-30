package com.sky.logistics.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.sky.logistics")
@Slf4j
public class LogisticsExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse<Map<String, Object>> handle(Exception exception) {
        log.error("logistics starter error", exception);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("reason", exception.getMessage());
        return ApiResponse.error(50001, "Starter server error", data);
    }
}
