package com.itstudio.club_backend.controller;

import com.itstudio.club_backend.dto.ApiResponse;
import com.itstudio.club_backend.entity.Member;
import com.itstudio.club_backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public ApiResponse<List<Member>> getAllMembers() {
        return ApiResponse.ok("success", memberService.getAllMembers());
    }

    @GetMapping("/members/active")
    public ApiResponse<List<Member>> getActiveMembers() {
        return ApiResponse.ok("success", memberService.getActiveMembers());
    }

    @GetMapping("/members/alumni")
    public ApiResponse<List<Member>> getAlumniMembers() {
        return ApiResponse.ok("success", memberService.getAlumniMembers());
    }
}