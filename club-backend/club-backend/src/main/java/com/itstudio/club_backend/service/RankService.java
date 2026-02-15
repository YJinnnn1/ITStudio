package com.itstudio.club_backend.service;

import com.itstudio.club_backend.dto.ApiResponse;
import com.itstudio.club_backend.dto.RankUpdateRequest;
import com.itstudio.club_backend.entity.RankRecord;
import com.itstudio.club_backend.repository.RankRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RankService {

    private final RankRecordRepository rankRecordRepository;

    public List<RankRecord> getRankByGame(String game) {
        return rankRecordRepository.findByGameOrderBySortOrderAsc(game);
    }

    public ApiResponse<String> updateRank(RankUpdateRequest request) {
        // 查找该游戏里是否已有这个玩家
        List<RankRecord> existing = rankRecordRepository
                .findByGameOrderBySortOrderAsc(request.getGame());

        RankRecord record = existing.stream()
                .filter(r -> r.getPlayerName().equals(request.getPlayerName()))
                .findFirst()
                .orElse(new RankRecord()); // 没有就新建

        record.setGame(request.getGame());
        record.setPlayerName(request.getPlayerName());
        record.setRankName(request.getRankName());
        record.setScore(request.getScore());
        record.setAvatarSeed(request.getAvatarSeed());
        record.setUpdatedAt(java.time.LocalDateTime.now());

        // 新玩家排在最后
        if (record.getId() == null) {
            record.setSortOrder(existing.size() + 1);
        }

        rankRecordRepository.save(record);
        return ApiResponse.ok("段位更新成功！", null);
    }
}