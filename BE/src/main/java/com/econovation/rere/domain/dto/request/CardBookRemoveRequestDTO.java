package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookRemoveRequestDTO {

    @NotNull(message = "please write cardbookId")
    private Integer cardbookId;

}
