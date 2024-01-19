package com.econovation.rere.domain.dto.response;


import com.econovation.rere.domain.dto.request.CardCreateRequestDTO;
import com.econovation.rere.domain.dto.request.CardUpdateRequestDTO;
import com.econovation.rere.domain.entity.Card;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//  메인 페이지에 카드북 하나 하나를 보여줄 때 필요한 데이터들을 담는 클래스
//  Create, Update 종류로 구분하지 않은 이유는 어차피 Response로 보낼 형식이 똑같기 때문이다.
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardResponseDTO {

    private Integer cardId;
    private String content;
    private String answer;

    public static CardResponseDTO toCardResponseDTO(Card card){
        return CardResponseDTO.builder()
                .cardId(card.getCardId())
                .content(card.getContent())
                .answer(card.getAnswer())
                .build();
    }

    public static List<CardResponseDTO> toCardResponseDTOS(List<Card> cards){
        List<CardResponseDTO> cardResponseDTOS = new ArrayList<>();

        for(Card card : cards) cardResponseDTOS.add(toCardResponseDTO(card));

        return cardResponseDTOS;
    }

}
