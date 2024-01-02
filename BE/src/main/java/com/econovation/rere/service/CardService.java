package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.CardCreateRequestDTO;
import com.econovation.rere.domain.dto.request.CardUpdateRequestDTO;
import com.econovation.rere.domain.dto.response.CardResponseDTO;
import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.Theme;
import com.econovation.rere.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
@Slf4j
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDTO register(CardCreateRequestDTO cardCreateRequestDTO, Theme theme, LocalDateTime timenow){
        Card card = Card.builder()
                .content(cardCreateRequestDTO.getContent())
                .answer(cardCreateRequestDTO.getAnswer())
                .updateDate(timenow)
                .createDate(timenow)
                .theme(theme)
                .build();
        card = cardRepository.save(card);

        return toCardResponseDTO(card);

    }

    private CardResponseDTO toCardResponseDTO(Card card){
        return CardResponseDTO.builder()
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
