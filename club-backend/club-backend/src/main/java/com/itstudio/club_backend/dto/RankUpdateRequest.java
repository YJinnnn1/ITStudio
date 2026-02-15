package com.itstudio.club_backend.dto;

import lombok.Data;

@Data
public class RankUpdateRequest {
    private String game;
    private String playerName;
    private String rankName;
    private String score;
    private String avatarSeed;
}