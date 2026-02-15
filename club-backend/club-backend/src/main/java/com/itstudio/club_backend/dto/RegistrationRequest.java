package com.itstudio.club_backend.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private Long activityId;
    private String studentName;
    private String studentId;
    private String contact;
}
