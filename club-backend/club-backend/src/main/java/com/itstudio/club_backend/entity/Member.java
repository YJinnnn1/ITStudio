package com.itstudio.club_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private String avatar;
    private String github;
    private String skills;
    private String grade;
    private String department;
    private Boolean isActive;
}