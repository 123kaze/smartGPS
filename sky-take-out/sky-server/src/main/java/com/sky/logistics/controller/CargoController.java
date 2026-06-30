package com.sky.logistics.controller;

import com.sky.logistics.common.ApiResponse;
import com.sky.logistics.common.PageResponse;
import com.sky.logistics.service.LogisticsStarterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cargo")
public class CargoController {

    private final LogisticsStarterService starterService;

    public CargoController(LogisticsStarterService starterService) {
        this.starterService = starterService;
    }

    @GetMapping
    public ApiResponse<PageResponse<Map<String, Object>>> list(@RequestParam(required = false) String status,
                                                               @RequestParam(required = false) String keyword,
                                                               @RequestParam(required = false) Integer page,
                                                               @RequestParam(required = false) Integer size) {
        return ApiResponse.success(starterService.cargo(status, keyword, page, size));
    }

    @PostMapping
    public ApiResponse<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        return ApiResponse.success(starterService.createCargo(request));
    }

    @GetMapping("/{cargoId}")
    public ApiResponse<Map<String, Object>> detail(@PathVariable String cargoId) {
        return ApiResponse.success(starterService.cargoDetail(cargoId));
    }

    @PostMapping("/bind")
    public ApiResponse<Map<String, Object>> bind(@RequestBody Map<String, Object> request) {
        return ApiResponse.success(starterService.bindCargo(request));
    }

    @PostMapping("/unbind")
    public ApiResponse<Map<String, Object>> unbind(@RequestBody Map<String, Object> request) {
        return ApiResponse.success(starterService.unbindCargo(request));
    }

    @PutMapping("/{cargoId}/status")
    public ApiResponse<Map<String, Object>> updateStatus(@PathVariable String cargoId, @RequestBody Map<String, Object> request) {
        return ApiResponse.success(starterService.updateCargoStatus(cargoId, request));
    }

    @GetMapping("/{cargoId}/status-logs")
    public ApiResponse<List<Map<String, Object>>> statusLogs(@PathVariable String cargoId) {
        return ApiResponse.success(starterService.cargoStatusLogs(cargoId));
    }

    @GetMapping("/{cargoId}/position")
    public ApiResponse<Map<String, Object>> position(@PathVariable String cargoId) {
        return ApiResponse.success(starterService.cargoPosition(cargoId));
    }

    @GetMapping("/{cargoId}/trajectory")
    public ApiResponse<Map<String, Object>> trajectory(@PathVariable String cargoId) {
        return ApiResponse.success(starterService.cargoTrajectory(cargoId));
    }

    @GetMapping("/{cargoId}/eta")
    public ApiResponse<Map<String, Object>> eta(@PathVariable String cargoId) {
        return ApiResponse.success(starterService.cargoEta(cargoId));
    }

    @GetMapping("/{cargoId}/timeline")
    public ApiResponse<Map<String, Object>> timeline(@PathVariable String cargoId) {
        return ApiResponse.success(starterService.cargoTimeline(cargoId));
    }
}
