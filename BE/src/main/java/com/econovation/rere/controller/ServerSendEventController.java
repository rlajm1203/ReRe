package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.domain.dto.response.StudyCompleteResponseDTO;
import com.econovation.rere.domain.sse.SseEmitters;
import com.econovation.rere.event.StudyAlarmEvent;
import com.econovation.rere.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class ServerSendEventController {

    private final SseEmitters sseEmitters;
    private final StudyService studyService;

    @GetMapping(value = "/connect", produces = "text/event-stream")
    public SseEmitter connect() {
//        emitter 생성, 추가
        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return emitter;
    }

    @GetMapping(value = "/studycheck")
    public SseEmitter connect(StudyAlarmEvent event){
        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return emitter;
    }

    @PostMapping(value = "/cardbook/{cardbookId}/themes/{userId}", produces = "text/event-stream")
    public void check(@PathVariable("cardbookId")Integer cardbookId, @PathVariable("userId")Integer userId){

        studyService.studyTimeCheck(cardbookId, userId);
        for(int i=0; i<10; i++){
            log.info("동시실행 되나?");
        }

    }

}