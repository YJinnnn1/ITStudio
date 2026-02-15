package com.itstudio.club_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data               // Lombok 自动生成 getter、setter、toString
@Entity             // 告诉 JPA：这个类对应数据库里的一张表
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID 自动递增
    private Long id;

    @Column(nullable = false)
    private String name;           // 活动名称

    private String description;    // 活动介绍

    private LocalDateTime activityDate;   // 活动时间

    private LocalDateTime deadline;       // 报名截止时间

    private LocalDateTime createdAt;      // 创建时间
}