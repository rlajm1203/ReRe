package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

//  Theme을 새로 생성할 때 보내는 요청 정보를 담는 클래스
//  Theme create와 Theme update는 요청 구조가 비슷하다.
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ThemeCreateRequestDTO {

    @NotEmpty(message = "please write themeName")
    private String name;

    @NotEmpty(message = "please write cardbookId")
    private Integer cardbookId;

//    Request 요청에는 하나의 시간 정보가 담겨있다.
//    따라서 이 정보가 theme을 create 하는 시간이면 createDate
//    theme을 update 하는 시간이면 updateDate가 된다.
//    @NotEmpty(message = "please write time now")
//    private LocalDateTime timenow;

//    theme 하나에 여러 카드가 존재하므로
    @NotEmpty(message = "please write cards")
    private List<CardCreateRequestDTO> cards;

}
