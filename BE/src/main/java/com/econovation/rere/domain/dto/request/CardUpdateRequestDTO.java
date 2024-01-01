package com.econovation.rere.domain.dto.request;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


//  Card 내용을 수정할 때 요청 정보를 담는 클래스
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardUpdateRequestDTO {
    @NotEmpty(message = "please write content")
    private String content;

    @NotEmpty(message = "please write answer")
    private String answer;

    @NotEmpty(message = "please write time now")
    private LocalDateTime nowtime;
}
