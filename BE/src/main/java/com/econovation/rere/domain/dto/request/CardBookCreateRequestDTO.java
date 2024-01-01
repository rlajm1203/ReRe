package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookCreateRequestDTO {

    @NotEmpty(message = "please write cardbook name")
    private String name;

    @NotEmpty(message = "please write cardbook writer")
    private Integer userId;

//    시간 정보는 프론트에서 전송 시점을 기준으로 하는 게 아니라
//    백엔드가 요청을 받은 시점을 기준으로 해야하는 듯

//    @NotEmpty(message = "please write time now")
//    private LocalDateTime timenow;

}
