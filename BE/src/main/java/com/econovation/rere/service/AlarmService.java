package com.econovation.rere.service;

import com.econovation.rere.controller.ServerSendEventController;
import com.econovation.rere.event.StudyAlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class AlarmService {

    public SseEmitter connect(Integer userId){

        SseEmitter emitter = new SseEmitter(60*1000L);

        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("userId : "+userId+" connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        emitter 생성 후 저장소에 추가
        ServerSendEventController.sseEmitters.put(userId, emitter);

//        emitter가 어떤 이유로든 정상적으로 동작하지 않았을 땐, 삭제
        emitter.onCompletion(()->ServerSendEventController.sseEmitters.remove(userId));
        emitter.onTimeout(()->ServerSendEventController.sseEmitters.remove(userId));
        emitter.onError((e)->ServerSendEventController.sseEmitters.remove(userId));

        return emitter;
    }

    @EventListener
    public void alarm(StudyAlarmEvent event){
        Integer userId = event.getUserId();
        SseEmitter emitter = ServerSendEventController.sseEmitters.get(userId);
        log.info(emitter.toString());

        try {
            emitter.send(SseEmitter.event()
                    .name(event.getMessage())
                    .data(event));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        emitter.onCompletion(()->ServerSendEventController.sseEmitters.remove(userId));
        emitter.onTimeout(()->ServerSendEventController.sseEmitters.remove(userId));
        emitter.onError((e)->ServerSendEventController.sseEmitters.remove(userId));
    }
}