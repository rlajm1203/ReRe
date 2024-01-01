package com.econovation.rere.domain.dto.request;

// 이 클래스는 실제로 전송되지 않는 데이터이지만, 만약을 위해 만들어 놓습니다.

import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlindCardCreateRequestDTO {

//    이게 맞나..?
    @NotEmpty(message = "please write card id")
    private Integer cardId;

}
