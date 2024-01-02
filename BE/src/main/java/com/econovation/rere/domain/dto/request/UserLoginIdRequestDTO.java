package com.econovation.rere.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserLoginIdRequestDTO {
    @NotEmpty(message = "Please write ID")
    private String loginId;

    @Builder
    public UserLoginIdRequestDTO(String loginId) {
        this.loginId = loginId;
    }
}
