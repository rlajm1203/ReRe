package com.econovation.rere.domain.dto.response;

import com.econovation.rere.domain.entity.UserCardBook;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserCardBookResponseDTO {
    private Integer userCardbookId;
    private String userName;
    private String cardbookName;
    private LocalDateTime chooseDate;

    public static UserCardBookResponseDTO toUserCardBookResponse(UserCardBook userCardBook){
        return UserCardBookResponseDTO.builder()
                .cardbookName(userCardBook.getCardbook().getName())
                .chooseDate(userCardBook.getChooseDate())
                .userCardbookId(userCardBook.getUserCardbookId())
                .userName(userCardBook.getUser().getNickname())
                .build();
    }


}
