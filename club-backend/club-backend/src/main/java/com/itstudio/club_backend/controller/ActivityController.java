package com.itstudio.club_backend.controller;

import com.itstudio.club_backend.dto.ApiResponse;
import com.itstudio.club_backend.dto.RegistrationRequest;
import com.itstudio.club_backend.entity.Activity;
import com.itstudio.club_backend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController               // 这是一个接口控制器，返回 JSON 数据
@RequestMapping("/api")       // 所有接口都以 /api 开头
@CrossOrigin(origins = "*")   // 允许前端跨域请求（开发阶段先放开）
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // GET 请求：查询所有活动
    // 访问地址：GET http://localhost:8080/api/activities
    @GetMapping("/activities")
    public ApiResponse<List<Activity>> getActivities() {
        return ApiResponse.ok("查询成功", activityService.getAllActivities());
    }

    // POST 请求：提交报名
    // 访问地址：POST http://localhost:8080/api/register
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegistrationRequest request) {
        return activityService.register(request);
    }
}