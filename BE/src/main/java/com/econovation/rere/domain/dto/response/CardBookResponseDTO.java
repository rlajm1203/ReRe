package com.econovation.rere.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime timenow;

}
