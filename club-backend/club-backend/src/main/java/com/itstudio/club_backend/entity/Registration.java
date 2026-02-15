package com.itstudio.club_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne                          // 多个报名对应一个活动
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Column(nullable = false)
    private String studentName;        // 姓名

    @Column(nullable = false, unique = true)
    private String studentId;          // 学号（唯一，防止重复报名）

    private String contact;            // 联系方式

    private LocalDateTime createdAt;
}