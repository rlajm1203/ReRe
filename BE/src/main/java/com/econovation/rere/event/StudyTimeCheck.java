package com.econovation.rere.event;

import com.econovation.rere.thread.StudyTimeCheckThread;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
@RequiredArgsConstructor
@Slf4j
public class StudyTimeCheck{
    private boolean userSchedule = false;
    private Integer cardbookId;
    private Integer userId;
    private final StudyTimeCheckThread studyTimeCheckThread;

    public void setUserSchedule(boolean userSchedule) {
        this.userSchedule = userSchedule;
    }
    public void setCardbookId(Integer cardbookId) {
        this.cardbookId = cardbookId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Scheduled(cron = "* * * * * ?") // 매 시간 30분마다 수행
    public void run(){

        try {
            if (userSchedule) {
                studyTimeCheckThread.setCardbookId(cardbookId);
                studyTimeCheckThread.setUserId(userId);
                studyTimeCheckThread.run();
            }
        } catch (Exception e){
            log.info(e.getMessage());
        }
    }

}
