package com.econovation.rere.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequestDTO {

    private Integer userId;

    private String loginId;

    private String pw;

    private String nickname;

}
