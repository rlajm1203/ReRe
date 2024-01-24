package com.econovation.rere.domain.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ThemePageResponseDTO {

    private String cardbookName;
    private List<ThemeResponseDTO> themes;

}
