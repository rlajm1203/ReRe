package com.econovation.rere.domain.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLoginResponseDTO {

    private String sessionId;
    private String nickname;

}
