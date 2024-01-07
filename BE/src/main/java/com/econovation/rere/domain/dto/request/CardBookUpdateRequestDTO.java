package com.econovation.rere.domain.dto.request;

import com.econovation.rere.domain.entity.CardBook;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

//  CardBook을 수정할 때 요청 정보를 담는 클래스

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookUpdateRequestDTO {

    @NotBlank(message = "please write new name")
    private String name;

    @NotBlank(message = "please write cardbookId")
    private Integer cardbookId;

    //    시간 정보는 프론트에서 전송 시점을 기준으로 하는 게 아니라
//    백엔드가 요청을 받은 시점을 기준으로 해야하는 듯

//    @NotEmpty(message = "please write time now")
//    private LocalDateTime timenow;

}
