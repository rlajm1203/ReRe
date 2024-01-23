package com.econovation.rere.domain.dto.response;

import com.econovation.rere.domain.entity.Card;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardPageResponseDTO {


    private String name;

    private List<CardResponseDTO> cards;

    public static CardPageResponseDTO toCardPageResponseDTO(String name, List<CardResponseDTO> cards){
        return CardPageResponseDTO.builder()
                .cards(cards)
                .name(name)
                .build();
    }
}
