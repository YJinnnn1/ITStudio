package com.itstudio.club_backend.repository;

import com.itstudio.club_backend.entity.RankRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RankRecordRepository extends JpaRepository<RankRecord, Long> {

    // 按游戏查询，按 sort_order 升序排列
    List<RankRecord> findByGameOrderBySortOrderAsc(String game);
}