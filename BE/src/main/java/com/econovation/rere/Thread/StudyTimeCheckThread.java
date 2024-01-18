package com.econovation.rere.Thread;

import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.StudyComplete;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.StudyCompleteRepository;
import com.econovation.rere.domain.repository.UserCardBookRepository;
import com.econovation.rere.domain.repository.UserRepository;
import com.econovation.rere.event.StudyAlarmEvent;
import com.econovation.rere.exception.CardBookNotFoundException;
import com.econovation.rere.exception.UserCardBookNotFoundException;
import com.econovation.rere.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StudyTimeCheckThread implements Runnable{

    private final UserRepository userRepository;

    private final CardBookRepository cardBookRepository;

    private final StudyCompleteRepository studyCompleteRepository;

    private final UserCardBookRepository userCardBookRepository;

    private final ApplicationEventPublisher publisher;

    private Integer userId;
    private Integer cardbookId;


    public void setCardbookId(Integer cardbookId) {
        this.cardbookId = cardbookId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void run(){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(() -> new CardBookNotFoundException());
        UserCardBook userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook, user).orElseThrow(() -> new UserCardBookNotFoundException());

        List<StudyComplete> studyCompletes = studyCompleteRepository.findAllByUserCardBook(userCardBook);

        for (StudyComplete studyComplete : studyCompletes) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime completeDate = studyComplete.getCompleteDate();

            long differ_sec = (Timestamp.valueOf(now).getTime() - Timestamp.valueOf(completeDate).getTime()) / 1000;

            if (differ_sec == 60 * 60 * 24 * 7)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .userId(userId)
                        .message("일주일이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());

            else if (differ_sec >= 60 * 60 * 24 * 3)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .userId(userId)
                        .message("3일이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());

            else if (differ_sec >= 60 * 60 * 24 * 1)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .userId(userId)
                        .message("1일이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());

            else if (differ_sec == 10)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .userId(userId)
                        .message("10초가 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());
        }
    }
}
