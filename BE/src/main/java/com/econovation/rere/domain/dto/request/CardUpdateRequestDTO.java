package com.econovation.rere.domain.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


//  Card 내용을 수정할 때 요청 정보를 담는 클래스
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardUpdateRequestDTO {

    @NotBlank(message = "please write cardId")
    private Integer cardId;

    @NotBlank(message = "please write content")
    private String content;

    @NotBlank(message = "please write answer")
    private String answer;

    //    시간 정보는 프론트에서 전송 시점을 기준으로 하는 게 아니라
//    백엔드가 요청을 받은 시점을 기준으로 해야하는 듯
//    @NotEmpty(message = "please write time now")
//    private LocalDateTime nowtime;

}
