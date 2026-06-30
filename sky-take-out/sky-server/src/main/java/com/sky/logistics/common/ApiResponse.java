package com.sky.logistics.common;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T data;
    private String timestamp;
    private String requestId;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = base();
        response.setCode(0);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static ApiResponse<Void> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> error(Integer code, String message, T data) {
        ApiResponse<T> response = base();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    private static <T> ApiResponse<T> base() {
        ApiResponse<T> response = new ApiResponse<>();
        response.setTimestamp(Instant.now().toString());
        response.setRequestId(UUID.randomUUID().toString());
        return response;
    }
}
