package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.CardCreateRequestDTO;
import com.econovation.rere.domain.dto.request.CardUpdateRequestDTO;
import com.econovation.rere.domain.dto.request.ThemeCreateRequestDTO;
import com.econovation.rere.domain.dto.request.ThemeUpdateRequestDTO;
import com.econovation.rere.domain.dto.response.ThemeResponseDTO;
import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.Theme;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.CardRepository;
import com.econovation.rere.domain.repository.ThemeRepository;
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
@Transactional(readOnly = false)
@Slf4j
public class ThemeService {

    private final CardBookRepository cardBookRepository;
    private final ThemeRepository themeRepository;
    private final CardService cardService;

//    생성
//    생성,수정할 때만 목차 정보와 카드 정보를 동시에 받는다.
    public ThemeResponseDTO register(ThemeCreateRequestDTO themeCreateRequestDTO, Integer cardbookId){
        LocalDateTime timenow = LocalDateTime.now();

        List<CardCreateRequestDTO> cardCreateRequestDTOS = themeCreateRequestDTO.getCards();
        List<Card> cardEntities = cardService.CreateDTOStoCardEntities(cardCreateRequestDTOS,timenow);

        Theme theme = themeCreateRequestDTO.toEntity(
                cardBookRepository.findById(cardbookId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카드북입니다.")),
                        cardEntities,
                        timenow);

        // Theme 저장하고, 저장한 theme 객체를 리턴하여 받음
        theme = themeRepository.save(theme);

        // Theme에 속한 Card들을 연관관계와 함께 저장
        for(CardCreateRequestDTO dto : themeCreateRequestDTO.getCards()){
            cardService.register(dto, theme, timenow);
        }
        return toThemeResponseDTO(theme);
    }

//    수정
//    생성,수정할 때만 목차 정보와 카드 정보를 동시에 받는다.
    public ThemeResponseDTO modify(ThemeUpdateRequestDTO themeUpdateRequestDTO, Integer themeId){
        LocalDateTime timenow = LocalDateTime.now();
        Theme theme = themeRepository.findById(themeId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 목차입니다."));

        theme.setName(themeUpdateRequestDTO.getName());
        theme.setCardList(cardService.UpdateDTOStoCardEntities(themeUpdateRequestDTO.getCards(),timenow));
        theme = themeRepository.save(theme);

//        카드 수정이 목차 수정과 동시에 이루어진다.
        for(CardUpdateRequestDTO dto : themeUpdateRequestDTO.getCards()){
            cardService.modify(dto,themeId,timenow);
        }

        return toThemeResponseDTO(theme);

    }

//    삭제
    public boolean remove(Integer themeId){
        Optional<Theme> ot = themeRepository.findById(themeId);
        if(themeRepository.deleteByThemeId(themeId)==1) return true;
        else return false;
    }

//    목차 페이지에서 (이전에 클릭한 카드북의) 모든 목차 조회
    public List<Theme> getAll(Integer cardbookId){
        CardBook cardBook = cardBookRepository.findById(cardbookId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카드북입니다."));
        return themeRepository.findAllByCardbook(cardBook);
    }


//    내부 메소드
    private ThemeResponseDTO toThemeResponseDTO(Theme theme){
        return ThemeResponseDTO.builder()
                .themeId(theme.getThemeId())
                .name(theme.getName())
                .build();
    }
    private List<ThemeResponseDTO> toThemeResponseDTOS(List<Theme> themes){
        List<ThemeResponseDTO> themeResponseDTOS = new ArrayList<>();
        int length = themes.size();

        for(Theme theme : themes){
            themeResponseDTOS.add(toThemeResponseDTO(theme));
        }

        return themeResponseDTOS;
    }
}
