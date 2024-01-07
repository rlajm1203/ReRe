package com.econovation.rere.domain.dto.request;

import com.econovation.rere.domain.entity.CardBook;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookCreateRequestDTO {

    @NotBlank(message = "please write cardbook name")
    private String name;

//    시간 정보는 프론트에서 전송 시점을 기준으로 하는 게 아니라
//    백엔드가 요청을 받은 시점을 기준으로 해야하는 듯

//    @NotEmpty(message = "please write time now")
//    private LocalDateTime timenow;

    public CardBook toEntity(String writer, LocalDateTime timenow){
        return CardBook.builder()
                .name(name)
                .writer(writer)
                .createDate(timenow)
                .updateDate(timenow)
                .build();
    }

}
