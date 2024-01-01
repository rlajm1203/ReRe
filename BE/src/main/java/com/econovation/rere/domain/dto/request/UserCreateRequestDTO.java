package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;


/*
이 클래스는 HttpRequest에 담긴 데이터를 담는 클래스로
UserCreate 요청은 UserId 값이 담겨서 오지 않는다.
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserCreateRequestDTO {

//  UserDTO 데이터를 받을 때는 UserId 값을 받지 않는다.
    @NotEmpty(message ="Please write ID")
    private String loginId;

    @NotEmpty(message ="Please write PassWord")
    private String pw;

    @NotEmpty(message ="Please write NickName")
    private String nickname;

}
