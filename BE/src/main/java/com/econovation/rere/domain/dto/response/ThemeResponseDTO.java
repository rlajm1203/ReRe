package com.econovation.rere.domain.dto.response;

import lombok.*;

//  메인 페이지에 카드북 하나 하나를 보여줄 때 필요한 데이터들을 담는 클래스
//  Create, Update 종류로 구분하지 않은 이유는 어차피 Response로 보낼 형식이 똑같기 때문이다.
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ThemeResponseDTO {

//  Theme 페이지에 cardbook 이름이 표시된다.
//    private String cardbookName;
//  추후 Card 조회를 위해 themeId를 보내야 함
    private Integer themeId;
    private String name;
    private Integer step; // 로그인 사용자일 경우엔 정수 값을, 비로그인 사용자일 경우에는 null 값을 할당하면 될까?

}
