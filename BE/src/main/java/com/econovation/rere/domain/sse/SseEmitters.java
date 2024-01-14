package com.econovation.rere.domain.sse;

import com.econovation.rere.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
@RequiredArgsConstructor
public class SseEmitters {

    private final StudyService studyService;

//  SseEmitter를 생성할 때는 비동기 요청이 완료되거나 타임아웃 발생 시 실행할 콜백을 등록할 수 있다.
//    타임아웃이 발생하면 브라우저에서 재연결 요청을 보내는데, 이때 새로운 Emitter 객체를 다시 생성한다.
//    그렇기 때문에 기존의 Emitter를 제거해야 한다.
//    따라서 onCompletion 콜백에서 자기 자신을 지우도록 동작한다.
//    주의할 점은 이 콜백이 SseEmitter를 관리하는 다른 스레드에서 실행된다는 것이다.
//    따라서 thread-safe 한 자료구조를 사용하지 않으면 ConcurrentModificationException이 발생할 수 있다.
//    여기선 CopyOnWriteArrayList를 사용했다.
    private final List<SseEmitter> emitters;

    public SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

//    연결된 모든 브라우저에 알림을 전달하는 건 쉽지만 다른건..?
//    public void studyTimeAlarm(){
//        emitters.forEach();
//    }

//    예시 메소드
//    public void count() {
//        long count = counter.incrementAndGet();
//        emitters.forEach(emitter -> {
//            try {
//                emitter.send(SseEmitter.event()
//                        .name("count")
//                        .data(count));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
}
