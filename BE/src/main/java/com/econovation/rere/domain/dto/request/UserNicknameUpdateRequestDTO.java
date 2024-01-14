package com.econovation.rere.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNicknameUpdateRequestDTO {
    @NotEmpty
    private String nickname;
    @Builder
    public UserNicknameUpdateRequestDTO(String nickname) {
        this.nickname = nickname;
    }
}
