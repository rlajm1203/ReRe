package com.econovation.rere.domain.dto.request;


import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//  사용자의 로그인 요청 정보를 저장하는 클래스

@Getter @Setter
@AllArgsConstructor
public class UserLoginRequestDTO {


    @NotBlank(message = "please write ID")
    private String loginID;

    @NotBlank(message = "please write PW")
    private String pw;

}
