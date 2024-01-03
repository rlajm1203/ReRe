package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookRemoveRequestDTO {

    @NotEmpty(message = "please write cardbookId")
    private Integer cardbookId;

}
