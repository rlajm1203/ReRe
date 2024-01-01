package com.econovation.rere.domain.dto.request;

import com.econovation.rere.domain.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


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
    @Size(min = 6, max = 12, message = "ID는 6자 이상 12자 이하입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$", message = "알파벳 소문자 및 숫자만 사용할 수 있습니다.")
    private String loginId;

    @NotEmpty(message ="Please write PassWord")
    @Size(min = 8, max = 15, message = "PW는 8자 이상 15자 이하입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@#$%^&+=!~`<>,./?;:'\\\"\\\\[\\\\]{}\\\\\\\\()|_-])\\\\S*$", message = "알파벳 대소문자 및 숫자, 특수문자가 포함되어야 합니다.")
    private String pw;

    @NotEmpty(message ="Please write NickName")
    @Size(min = 2, max = 12, message = "닉네임은 2자 이상 12자 이하입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "닉네임은 한글,알파벳 대소문자,숫자를 모두 사용할 수 있습니다.")
    private String nickname;

    public User toEntity(){

        return User.builder()
                .loginId(loginId)
                .pw(pw)
                .nickname(nickname)
                .build();
    }

}
