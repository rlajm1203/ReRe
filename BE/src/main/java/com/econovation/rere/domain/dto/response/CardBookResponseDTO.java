package com.econovation.rere.domain.dto.response;

import com.econovation.rere.domain.entity.CardBook;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//  메인 페이지에 카드북 하나 하나를 보여줄 때 필요한 데이터들을 담는 클래스
// Create, Update 종류로 구분하지 않은 이유는 어차피 Response로 보낼 형식이 똑같기 때문이다.
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookResponseDTO {

    private Integer cardbookId;
    private String name;
    private String writer;
    private LocalDateTime updateDate;
    private String imageUrl;

    public static CardBookResponseDTO toCardBookResponseDTO(CardBook cardBook){
        String imageUrl = "/cardbook/" + cardBook.getCardbookId() + "/image";
        return CardBookResponseDTO.builder()
                .writer(cardBook.getWriter())
                .name(cardBook.getName())
                .updateDate(cardBook.getUpdateDate())
                .cardbookId(cardBook.getCardbookId())
                .imageUrl(imageUrl)
                .build();
    }

    public static List<CardBookResponseDTO> toCardBookResponseDTOS(List<CardBook> cardBooks){
        List<CardBookResponseDTO> cardBookResponseDTOS = new ArrayList<>();
        int length = cardBooks.size();
        for(int i=0; i<length; i++){
            CardBook cardBook = cardBooks.get(i);
            cardBookResponseDTOS.add(
                    toCardBookResponseDTO(cardBook)
            );
        }
        return cardBookResponseDTOS;
    }

}
