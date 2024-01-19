package com.econovation.rere.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPwUpdateRequestDTO {
    @NotEmpty
    private String pw;
    @Builder
    public UserPwUpdateRequestDTO(String pw) {
        this.pw = pw;
    }
}
