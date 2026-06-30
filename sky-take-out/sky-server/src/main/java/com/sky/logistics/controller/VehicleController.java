package com.sky.logistics.controller;

import com.sky.logistics.common.ApiResponse;
import com.sky.logistics.common.PageResponse;
import com.sky.logistics.service.LogisticsStarterService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final LogisticsStarterService starterService;

    public VehicleController(LogisticsStarterService starterService) {
        this.starterService = starterService;
    }

    @GetMapping
    public ApiResponse<PageResponse<Map<String, Object>>> list(@RequestParam(required = false) String status,
                                                               @RequestParam(required = false) String keyword,
                                                               @RequestParam(required = false) Integer page,
                                                               @RequestParam(required = false) Integer size) {
        return ApiResponse.success(starterService.vehicles(status, keyword, page, size));
    }

    @GetMapping("/{plate}")
    public ApiResponse<Map<String, Object>> detail(@PathVariable String plate) {
        return ApiResponse.success(starterService.vehicleDetail(plate));
    }

    @PostMapping
    public ApiResponse<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        return ApiResponse.success(starterService.createVehicle(request));
    }

    @PutMapping("/{plate}")
    public ApiResponse<Map<String, Object>> update(@PathVariable String plate, @RequestBody Map<String, Object> request) {
        if (request == null) {
            request = new LinkedHashMap<>();
        }
        request.put("plate", plate);
        return ApiResponse.success(request);
    }

    @DeleteMapping("/{plate}")
    public ApiResponse<Map<String, Object>> delete(@PathVariable String plate) {
        return ApiResponse.success(starterService.activeVehicleTask(plate));
    }

    @GetMapping("/{plate}/active-task")
    public ApiResponse<Map<String, Object>> activeTask(@PathVariable String plate) {
        return ApiResponse.success(starterService.activeVehicleTask(plate));
    }

    @PostMapping("/{plate}/command")
    public ApiResponse<Map<String, Object>> createCommand(@PathVariable String plate, @RequestBody Map<String, Object> request) {
        return ApiResponse.success(starterService.createCommand(plate, request));
    }

    @GetMapping("/{plate}/command/{commandId}")
    public ApiResponse<Map<String, Object>> commandDetail(@PathVariable String plate, @PathVariable String commandId) {
        return ApiResponse.success(starterService.commandDetail(plate, commandId));
    }

    @GetMapping("/{plate}/commands")
    public ApiResponse<PageResponse<Map<String, Object>>> commands(@PathVariable String plate,
                                                                   @RequestParam(required = false) Integer page,
                                                                   @RequestParam(required = false) Integer size) {
        return ApiResponse.success(starterService.commands(plate, page, size));
    }
}
