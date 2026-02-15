package com.itstudio.club_backend.controller;

import com.itstudio.club_backend.dto.ApiResponse;
import com.itstudio.club_backend.dto.RankUpdateRequest;
import com.itstudio.club_backend.entity.RankRecord;
import com.itstudio.club_backend.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    // GET http://localhost:8080/api/rank/valorant
    // GET http://localhost:8080/api/rank/naraka
    @GetMapping("/rank/{game}")
    public ApiResponse<List<RankRecord>> getRank(@PathVariable String game) {
        return ApiResponse.ok("success", rankService.getRankByGame(game));
    }
    // POST http://localhost:8080/api/rank/update
    @PostMapping("/rank/update")
    public ApiResponse<String> updateRank(@RequestBody RankUpdateRequest request) {
        return rankService.updateRank(request);
    }
}