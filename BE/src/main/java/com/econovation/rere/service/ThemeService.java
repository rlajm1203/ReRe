package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.ThemeResponseDTO;
import com.econovation.rere.domain.entity.*;
import com.econovation.rere.domain.repository.*;
import com.econovation.rere.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class ThemeService {

    private final CardBookRepository cardBookRepository;
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final UserCardBookRepository userCardBookRepository;
    private final StudyCompleteRepository studyCompleteRepository;
    private final CardService cardService;

//    생성
//    생성,수정할 때만 목차 정보와 카드 정보를 동시에 받는다.
    @Transactional(readOnly = false)
    public Boolean register(ThemeCreateRequestDTO themeCreateRequestDTO, Integer cardbookId) throws CardBookNotFoundException{
        LocalDateTime timenow = LocalDateTime.now();

        List<CardCreateRequestDTO> cardCreateRequestDTOS = themeCreateRequestDTO.getCards();
        List<Card> cardEntities = cardService.CreateDTOStoCardEntities(cardCreateRequestDTOS,timenow);

        Theme theme = themeCreateRequestDTO.toEntity(
                cardBookRepository.findById(cardbookId).orElseThrow(()->new CardBookNotFoundException()),
                        cardEntities,
                        timenow);

        // Theme 저장하고, 저장한 theme 객체를 리턴하여 받음
        theme = themeRepository.save(theme);

        // Theme에 속한 Card들을 연관관계와 함께 저장
        for(CardCreateRequestDTO dto : themeCreateRequestDTO.getCards()){
            cardService.register(dto, theme, timenow);
        }

        return Boolean.TRUE;
    }

//    수정
//    생성,수정할 때만 목차 정보와 카드 정보를 동시에 받는다.
    @Transactional(readOnly = false)
    public Boolean update(ThemeUpdateRequestDTO themeUpdateRequestDTO, Integer themeId) throws ThemeNotFoundException{
        LocalDateTime timenow = LocalDateTime.now();
        Theme theme = themeRepository.findById(themeId).orElseThrow(()->new ThemeNotFoundException());

        theme.setName(themeUpdateRequestDTO.getName());

        if(cardService.removeAllByTheme(theme)>0) {
////        기존에 존재하던 카드 전부 삭제
////        카드 수정이 목차 수정과 동시에 이루어진다.
            for (CardUpdateRequestDTO dto : themeUpdateRequestDTO.getCards()){
                cardService.update(dto, theme, timenow);
            }
        }
        return Boolean.TRUE;
    }

//    삭제
    @Transactional(readOnly = false)
    public Boolean remove(Integer themeId){
        if(themeRepository.deleteByThemeId(themeId)==1) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

//    (로그인 사용자용) 목차 페이지에서 (이전에 클릭한 카드북의) 모든 목차 조회
    public List<ThemeResponseDTO> getAll(Integer cardbookId, Integer userId) throws CardBookNotFoundException{
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()->new CardBookNotFoundException());
        return toThemeResponseDTOS(themeRepository.findAllByCardbook(cardBook), cardbookId, userId);
    }

//    (비로그인 사용자용) 목차 페이지에서 (이전에 클릭한 카드북의) 모든 목차 조회
    public List<ThemeResponseDTO> getAllNotLogin(Integer cardbookId) throws CardBookNotFoundException{
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()->new CardBookNotFoundException());

        return toThemeResponseDTOS(themeRepository.findAllByCardbook(cardBook), cardbookId, null);
    }
//    내부 메소드
    private ThemeResponseDTO toThemeResponseDTO(Theme theme, Integer cardbookId, Integer userId){

        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()->new CardBookNotFoundException());
        User user;
        UserCardBook userCardBook;
        StudyComplete studyComplete;


        try {
            if(userId!=null) {
                user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
                userCardBook = userCardBookRepository.findByCardbookAndUser(cardBook, user).orElseThrow(() -> new UserCardBookNotFoundException());
                studyComplete = studyCompleteRepository.findByUserCardBookAndTheme(userCardBook, theme).orElseThrow(() -> new StudyCompleteNotFoundException("사용자가 학습한 이력이 없습니다."));
                return ThemeResponseDTO.builder()
                        .themeId(theme.getThemeId())
                        .name(theme.getName())
                        .step(studyComplete.getStep())
                        .build();
            }
            return ThemeResponseDTO.builder()
                    .themeId(theme.getThemeId())
                    .name(theme.getName())
                    .step(null)
                    .build();
            // 사용자가 한번이라도 학습을 하지 않았을 경우
        } catch (StudyCompleteNotFoundException e1){
            // 사용자가 한번도 학습을 하지 않았을 경우
            return ThemeResponseDTO.builder()
                    .themeId(theme.getThemeId())
                    .name(theme.getName())
                    .step(null)
                    .build();
        }
    }

    private List<ThemeResponseDTO> toThemeResponseDTOS(List<Theme> themes,Integer cardbookId, Integer userId){
        List<ThemeResponseDTO> themeResponseDTOS = new ArrayList<>();
        int length = themes.size();

        for(Theme theme : themes){
            themeResponseDTOS.add(toThemeResponseDTO(theme, cardbookId, userId));
        }
        return themeResponseDTOS;
    }
}