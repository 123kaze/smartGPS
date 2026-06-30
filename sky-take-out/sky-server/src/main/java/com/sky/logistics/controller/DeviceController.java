package com.sky.logistics.controller;

import com.sky.logistics.common.ApiResponse;
import com.sky.logistics.common.PageResponse;
import com.sky.logistics.service.LogisticsStarterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private final LogisticsStarterService starterService;

    public DeviceController(LogisticsStarterService starterService) {
        this.starterService = starterService;
    }

    @GetMapping("/status")
    public ApiResponse<Map<String, Object>> status(@RequestParam(required = false) String status,
                                                   @RequestParam(required = false) String keyword,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size) {
        return ApiResponse.success(starterService.deviceStatus(status, keyword, page, size));
    }

    @GetMapping("/{imei}")
    public ApiResponse<Map<String, Object>> detail(@PathVariable String imei) {
        return ApiResponse.success(starterService.deviceDetail(imei));
    }

    @GetMapping("/{imei}/heartbeats")
    public ApiResponse<List<Map<String, Object>>> heartbeats(@PathVariable String imei) {
        return ApiResponse.success(starterService.deviceHeartbeats(imei));
    }

    @GetMapping("/{imei}/cargo-events")
    public ApiResponse<PageResponse<Map<String, Object>>> cargoEvents(@PathVariable String imei,
                                                                      @RequestParam(required = false) Integer page,
                                                                      @RequestParam(required = false) Integer size) {
        return ApiResponse.success(starterService.deviceCargoEvents(imei, page, size));
    }
}
