package com.econovation.rere.event;

import com.econovation.rere.Thread.StudyTimeCheckThread;
import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.StudyComplete;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.StudyCompleteRepository;
import com.econovation.rere.domain.repository.UserCardBookRepository;
import com.econovation.rere.domain.repository.UserRepository;
import com.econovation.rere.exception.CardBookNotFoundException;
import com.econovation.rere.exception.UserCardBookNotFoundException;
import com.econovation.rere.exception.UserNotFoundException;
import com.econovation.rere.service.StudyService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
