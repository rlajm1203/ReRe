package com.econovation.rere.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserNicknameRequestDTO {
    @NotBlank(message = "Please write nickname")
    private String nickname;

    @Builder
    public UserNicknameRequestDTO(String nickname) {
        this.nickname = nickname;
    }
}
