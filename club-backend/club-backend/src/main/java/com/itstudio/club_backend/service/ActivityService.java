package com.itstudio.club_backend.service;

import com.itstudio.club_backend.dto.ApiResponse;
import com.itstudio.club_backend.dto.RegistrationRequest;
import com.itstudio.club_backend.entity.Activity;
import com.itstudio.club_backend.entity.Registration;
import com.itstudio.club_backend.repository.ActivityRepository;
import com.itstudio.club_backend.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service                  // 告诉 Spring：这是一个服务类，帮我管理它
@RequiredArgsConstructor  // Lombok 自动生成构造函数，实现依赖注入
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final RegistrationRepository registrationRepository;

    // 查询所有活动
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // 报名活动
    public ApiResponse<String> register(RegistrationRequest request) {

        // 1. 检查活动是否存在
        Activity activity = activityRepository.findById(request.getActivityId())
                .orElse(null);
        if (activity == null) {
            return ApiResponse.fail("活动不存在");
        }

        // 2. 检查报名是否已截止
        if (LocalDateTime.now().isAfter(activity.getDeadline())) {
            return ApiResponse.fail("报名已截止");
        }

        // 3. 检查是否重复报名（同一学号）
        if (registrationRepository.existsByStudentId(request.getStudentId())) {
            return ApiResponse.fail("你已经报名过了");
        }

        // 4. 保存报名信息
        Registration reg = new Registration();
        reg.setActivity(activity);
        reg.setStudentName(request.getStudentName());
        reg.setStudentId(request.getStudentId());
        reg.setContact(request.getContact());
        reg.setCreatedAt(LocalDateTime.now());

        registrationRepository.save(reg);

        return ApiResponse.ok("报名成功！", null);
    }
}