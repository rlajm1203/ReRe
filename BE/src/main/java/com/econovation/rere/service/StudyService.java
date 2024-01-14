package com.econovation.rere.service;

import com.econovation.rere.domain.dto.response.StudyCompleteResponseDTO;
import com.econovation.rere.domain.entity.*;
import com.econovation.rere.domain.repository.*;
import com.econovation.rere.event.StudyAlarmEvent;
import com.econovation.rere.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class StudyService {

    private final StudyCompleteRepository studyCompleteRepository;
    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final CardBookRepository cardBookRepository;
    private final ApplicationEventPublisher publisher;

//    학습 완료 메소드
    @Transactional(readOnly = false)
    public StudyCompleteResponseDTO studyComplete(Integer cardbookId, Integer themeId, Integer userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());
        Theme theme = themeRepository.findById(themeId).orElseThrow(()->new ThemeNotFoundException());
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()-> new CardBookNotFoundException());

        UserCardBook userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook, user).orElseThrow(()-> new UserCardBookNotFoundException());

        return StudyCompleteResponseDTO.toStudyCompleteResponseDTO(studyCompleteRepository.save(studyStepCheck(cardbookId, themeId, userId)), user.getNickname());
    }

//    시간별로 학습을 해야 하는지 체크하는 메소드
    public void studyTimeCheck(Integer cardbookId, Integer userId){

        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()->new CardBookNotFoundException());
        UserCardBook userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook,user).orElseThrow(()->new UserCardBookNotFoundException());

        List<StudyComplete> studyCompletes = studyCompleteRepository.findAllByUserCardBook(userCardBook);

        for(StudyComplete studyComplete : studyCompletes){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime completeDate = studyComplete.getCompleteDate();

            long differ_mills = (Timestamp.valueOf(now).getTime() - Timestamp.valueOf(completeDate).getTime())/1000;

            if(differ_mills==60*60*24*7)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .message("일주일이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());

            else if(differ_mills>=60*60*24*3)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .message("3일이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());

            else if(differ_mills>=60*60*24*1)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .message("1일이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());

            else if(differ_mills==60*60)
                publisher.publishEvent(StudyAlarmEvent.builder()
                        .message("1시간이 지났습니다. 학습해주세요.")
                        .themeId(studyComplete.getTheme().getThemeId())
                        .step(studyComplete.getStep())
                        .build());
        }
    }

//    사용자가 현재 학습한 목차가 몇 단계인지 체크하는 메소드
    private StudyComplete studyStepCheck(Integer cardbookId, Integer themeId, Integer userId){

        LocalDateTime timenow = LocalDateTime.now();

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(() -> new CardBookNotFoundException());
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new ThemeNotFoundException());

        UserCardBook userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook, user).orElseThrow(() -> new UserCardBookNotFoundException());

        if(studyCompleteRepository.existsByUserCardBookAndTheme(userCardBook, theme)){
            StudyComplete studyComplete = studyCompleteRepository.findByUserCardBookAndTheme(userCardBook, theme).orElseThrow(()->new StudyCompleteNotFoundException());
            studyComplete.setStep((studyComplete.getStep()+1)%5);
            studyComplete.setCount(studyComplete.getCount()+1);
            return studyComplete;
        }
        else{
            return StudyComplete.builder()
                    .step(0)
                    .count(0)
                    .completeDate(timenow)
                    .theme(theme)
                    .userCardBook(userCardBook)
                    .build();
        }
    }
}
