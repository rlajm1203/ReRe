package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ThemeRemoveRequestDTO {

    @NotBlank(message = "please write cardbookId")
    private Integer cardbookId;

    @NotBlank(message = "please write themeId")
    private Integer themeId;

}
