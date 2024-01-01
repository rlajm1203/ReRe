package com.econovation.rere.domain.dto.request;


import lombok.*;

import javax.validation.constraints.NotEmpty;

//  사용자의 로그인 요청 정보를 저장하는 클래스
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLoginRequestDTO {

    @NotEmpty(message = "please write ID")
    private String loginID;

    @NotEmpty(message = "please write PW")
    private String pw;

}
