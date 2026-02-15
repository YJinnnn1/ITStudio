package com.itstudio.club_backend.controller;

import com.itstudio.club_backend.dto.AiRequest;
import com.itstudio.club_backend.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ai/chat")
    public SseEmitter chat(@RequestBody AiRequest request) {
        SseEmitter emitter = new SseEmitter(60_000L); // 60秒超时
        aiService.streamChat(request.getMessage(), emitter);
        return emitter;
    }
}