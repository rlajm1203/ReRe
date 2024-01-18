package com.econovation.rere.controller;


import com.econovation.rere.domain.entity.User;

import com.econovation.rere.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class ServerSendEventController {

    public static Map<Integer, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    private final AlarmService alarmService;

    @GetMapping(value = "/connect", produces = "text/event-stream")
    public SseEmitter connect(HttpServletRequest request) {
        log.info("SSE 연결 요청");
        Integer userId = ((User)request.getSession().getAttribute("USER")).getUserId();
//        emitter 생성, 추가
        SseEmitter emitter = alarmService.connect(userId);

        return emitter;
    }
}