package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.CardCreateRequestDTO;
import com.econovation.rere.domain.dto.request.CardUpdateRequestDTO;
import com.econovation.rere.domain.dto.response.CardResponseDTO;
import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.Theme;
import com.econovation.rere.domain.repository.CardRepository;
import com.econovation.rere.domain.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class CardService {

    private final CardRepository cardRepository;
    private final ThemeRepository themeRepository;

//    생성
    @Transactional(readOnly = false)
    public void register(CardCreateRequestDTO cardCreateRequestDTO, Theme theme, LocalDateTime timenow){
        Card card = Card.builder()
                .content(cardCreateRequestDTO.getContent())
                .answer(cardCreateRequestDTO.getAnswer())
                .updateDate(timenow)
                .createDate(timenow)
                .theme(theme)
                .build();
        card = cardRepository.save(card);
    }

//    수정
    //    기존에 목차에 속한 카드를 전부 삭제하고 새로운 카드를 다시 넣을 것이냐. <- 이걸로 선택
    //    기존에 목차에 속한 카드를 가져와서 set메소드로 변경 후 다시 저장할 것이냐.
    //    카드는 수정하게 되면, 원래 카드를 삭제하고 수정된 내용의 카드를 생성하는 방식이므로 수정할 때마다 Id가 바뀐다.
    @Transactional(readOnly = false)
    public void update(CardUpdateRequestDTO cardUpdateRequestDTO, Theme theme, LocalDateTime timenow){
        Card old_card = cardRepository.findById(cardUpdateRequestDTO.getCardId())
                .orElseThrow(()->new IllegalArgumentException("해당 카드는 존재하지 않습니다."));

        Card card = Card.builder()
                .content(cardUpdateRequestDTO.getContent())
                .answer(cardUpdateRequestDTO.getAnswer())
                .updateDate(timenow)
                .createDate(old_card.getCreateDate())
                .theme(theme)
                .build();
    }

//    삭제
    @Transactional(readOnly = false)
    public boolean remove(Integer cardId){
        if(cardRepository.deleteByCardId(cardId)==1) return true;
        else return false;
    }

//    해당 theme에 속하는 모든 카드를 조회
    public List<Card> getAll(Integer themeId){
        return cardRepository.findAllByTheme(
                themeRepository.findById(themeId).orElseThrow(()->new IllegalArgumentException("해당 목차가 존재하지 않습니다."))
        );
    }

//    목차를 수정할 때 목차에 속한 카드들도 같이 수정하므로 필요하다.
    public Integer removeAllByTheme(Theme theme){
        return cardRepository.deleteAllByTheme(theme);
    }


    private CardResponseDTO toCardResponseDTO(Card card){
        return CardResponseDTO.builder()
                .cardId(card.getCardId())
                .content(card.getContent())
                .answer(card.getAnswer())
                .build();
    }
    List<Card> CreateDTOStoCardEntities(List<CardCreateRequestDTO> cardCreateRequestDTOS, LocalDateTime timenow){
        List<Card> cards = new ArrayList<>();
        for(CardCreateRequestDTO dto : cardCreateRequestDTOS){
            cards.add(
                    Card.builder()
                            .createDate(timenow)
                            .updateDate(timenow)
                            .content(dto.getContent())
                            .answer(dto.getAnswer())
                            .build()
            );
        }

        return cards;
    }
    List<Card> UpdateDTOStoCardEntities(List<CardUpdateRequestDTO> cardUpdateRequestDTOS, LocalDateTime timenow){
        List<Card> cards = new ArrayList<>();
        for(CardUpdateRequestDTO dto : cardUpdateRequestDTOS){
            cards.add(
                    Card.builder()
                            .createDate(timenow)
                            .updateDate(timenow)
                            .content(dto.getContent())
                            .answer(dto.getAnswer())
                            .build()
            );
        }

        return cards;
    }

}
