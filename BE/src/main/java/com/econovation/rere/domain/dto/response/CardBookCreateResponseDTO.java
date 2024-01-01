package com.econovation.rere.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

//  메인 페이지에 카드북 하나 하나를 보여줄 때 필요한 데이터들을 담는 클래스
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookCreateResponseDTO {

    private Integer cardbookId;
    private String name;
    private String writer;
    private LocalDateTime timenow;

}
