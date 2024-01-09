package com.econovation.rere.service;

import com.econovation.rere.domain.dto.response.StudyCompleteResponseDTO;
import com.econovation.rere.domain.entity.*;
import com.econovation.rere.domain.repository.*;
import com.econovation.rere.exception.CardBookNotFoundException;
import com.econovation.rere.exception.ThemeNotFoundException;
import com.econovation.rere.exception.UserCardBookNotFoundException;
import com.econovation.rere.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Transactional(readOnly = false)
    public StudyCompleteResponseDTO firstStudyComplete(Integer cardbookId, Integer themeId, Integer userId){
        LocalDateTime timenow = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());
        Theme theme = themeRepository.findById(themeId).orElseThrow(()->new ThemeNotFoundException());
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()-> new CardBookNotFoundException());

        UserCardBook userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook, user).orElseThrow(()-> new UserCardBookNotFoundException());

        StudyComplete studyComplete = studyCompleteRepository.save(
                StudyComplete.builder()
                        .completeDate(timenow)
                        .userCardBook(userCardBook)
                        .theme(theme)
                        .step(0)
                        .build()
        );


        return StudyCompleteResponseDTO.toStudyCompleteResponseDTO(studyComplete, user.getNickname());
    }
}
