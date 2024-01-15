package com.econovation.rere.event;

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
import lombok.*;
import org.springframework.context.ApplicationEventPublisher;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyTimeCheck extends Thread{

    private UserRepository userRepository;
    private CardBookRepository cardBookRepository;
    private UserCardBookRepository userCardBookRepository;
    private StudyCompleteRepository studyCompleteRepository;
    private ApplicationEventPublisher publisher;
    private Integer userId;
    private Integer cardbookId;
    public void run(){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(() -> new CardBookNotFoundException());
        UserCardBook userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook, user).orElseThrow(() -> new UserCardBookNotFoundException());

        List<StudyComplete> studyCompletes = studyCompleteRepository.findAllByUserCardBook(userCardBook);

        while(true) {
            for (StudyComplete studyComplete : studyCompletes) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime completeDate = studyComplete.getCompleteDate();

                //
                long differ_sec = (Timestamp.valueOf(now).getTime() - Timestamp.valueOf(completeDate).getTime()) / 1000;

                if (differ_sec == 60 * 60 * 24 * 7)
                    publisher.publishEvent(StudyAlarmEvent.builder()
                            .message("일주일이 지났습니다. 학습해주세요.")
                            .themeId(studyComplete.getTheme().getThemeId())
                            .step(studyComplete.getStep())
                            .build());

                else if (differ_sec >= 60 * 60 * 24 * 3)
                    publisher.publishEvent(StudyAlarmEvent.builder()
                            .message("3일이 지났습니다. 학습해주세요.")
                            .themeId(studyComplete.getTheme().getThemeId())
                            .step(studyComplete.getStep())
                            .build());

                else if (differ_sec >= 60 * 60 * 24 * 1)
                    publisher.publishEvent(StudyAlarmEvent.builder()
                            .message("1일이 지났습니다. 학습해주세요.")
                            .themeId(studyComplete.getTheme().getThemeId())
                            .step(studyComplete.getStep())
                            .build());

                else if (differ_sec == 10)
                    publisher.publishEvent(StudyAlarmEvent.builder()
                            .message("10초가 지났습니다. 학습해주세요.")
                            .themeId(studyComplete.getTheme().getThemeId())
                            .step(studyComplete.getStep())
                            .build());
            }
        }
    }

    public void inputId(Integer cardbookId, Integer userId){
        this.userId = userId;
        this.cardbookId = cardbookId;
    }
}
