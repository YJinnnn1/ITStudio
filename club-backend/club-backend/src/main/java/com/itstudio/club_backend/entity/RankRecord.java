package com.itstudio.club_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rank_records")
public class RankRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String game;
    private String playerName;
    private String rankName;
    private String score;
    private String avatarSeed;
    private Integer sortOrder;
    private LocalDateTime updatedAt;
}