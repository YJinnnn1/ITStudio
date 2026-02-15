package com.itstudio.club_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class AiService {

    private static final String API_KEY = "sk-5b8e8bd5d491430ea8a1f021f9e872a0";
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";

    private static final String SYSTEM_PROMPT =
            "你是 ITSTUDIO IT研学会的 AI 助手。" +
                    "社团成立于2018年，是校级五星社团，专注于技术学习和电竞文化。" +
                    "主要部门：技术部（Java/Python/前端）、运营部、电竞部。" +
                    "每学期开学第一周招新。" +
                    "请用简洁友好的语气回答同学们关于社团的问题。" +
                    "如果问题与社团无关，也可以正常回答技术问题。";

    public void streamChat(String userMessage, SseEmitter emitter) {
        new Thread(() -> {
            try {
                // 构建请求体
                String requestBody = "{"
                        + "\"model\":\"deepseek-chat\","
                        + "\"stream\":true,"
                        + "\"max_tokens\":500,"
                        + "\"messages\":["
                        + "{\"role\":\"system\",\"content\":\"" + escapeJson(SYSTEM_PROMPT) + "\"},"
                        + "{\"role\":\"user\",\"content\":\"" + escapeJson(userMessage) + "\"}"
                        + "]}";

                // 发起 HTTP 请求
                HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
                conn.setDoOutput(true);
                conn.getOutputStream().write(requestBody.getBytes(StandardCharsets.UTF_8));

                // 逐行读取流式响应
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
                );

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("data: ")) {
                        String data = line.substring(6).trim();
                        if (data.equals("[DONE]")) break;

                        // 解析 delta.content
                        String content = extractContent(data);
                        if (content != null && !content.isEmpty()) {
                            emitter.send(SseEmitter.event().data(content));
                        }
                    }
                }

                reader.close();
                emitter.send(SseEmitter.event().name("done").data("[DONE]"));
                emitter.complete();

            } catch (Exception e) {
                try {
                    emitter.send(SseEmitter.event().name("error").data("AI 服务异常：" + e.getMessage()));
                    emitter.complete();
                } catch (Exception ignored) {}
            }
        }).start();
    }

    // 从 SSE JSON 里提取 delta.content
    private String extractContent(String json) {
        try {
            int idx = json.indexOf("\"delta\"");
            if (idx == -1) return null;
            int contentIdx = json.indexOf("\"content\"", idx);
            if (contentIdx == -1) return null;
            int start = json.indexOf("\"", contentIdx + 9) + 1;
            int end = json.indexOf("\"", start);
            // 处理转义字符
            return json.substring(start, end)
                    .replace("\\n", "\n")
                    .replace("\\t", "\t")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\");
        } catch (Exception e) {
            return null;
        }
    }

    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}