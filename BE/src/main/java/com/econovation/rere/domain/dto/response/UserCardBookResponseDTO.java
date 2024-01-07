package com.econovation.rere.domain.dto.response;

import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.UserCardBook;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserCardBookResponseDTO {
    private Integer userCardbookId;
    private String userName;
    private String cardbookName;
    private LocalDateTime chooseDate;

    public static UserCardBookResponseDTO toUserCardBookResponseDTO(UserCardBook userCardBook){
        return UserCardBookResponseDTO.builder()
                .cardbookName(userCardBook.getCardbook().getName())
                .chooseDate(userCardBook.getChooseDate())
                .userCardbookId(userCardBook.getUserCardbookId())
                .userName(userCardBook.getUser().getNickname())
                .build();
    }

    public static List<CardBookResponseDTO> toCardBookResponseDTOS(List<UserCardBook> userCardBooks){
        List<CardBook> cardBooks = new ArrayList<>();

        for(UserCardBook ucb : userCardBooks){
            System.out.println(ucb.getCardbook().getName());
            System.out.println(1);
            cardBooks.add(ucb.getCardbook());
        }

        return CardBookResponseDTO.toCardBookResponseDTOS(cardBooks);
    }

}
