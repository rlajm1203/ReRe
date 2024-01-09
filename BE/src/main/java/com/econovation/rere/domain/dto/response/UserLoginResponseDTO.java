package com.econovation.rere.domain.dto.response;

import lombok.*;

//  로그인에 성공하면, SessionID와 맞는 닉네임을 전달한다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginResponseDTO {

    private String sessionId;
    private String nickname;

    @Builder
    public UserLoginResponseDTO(String sessionId, String nickname) {
        this.sessionId = sessionId;
        this.nickname = nickname;
    }

}
