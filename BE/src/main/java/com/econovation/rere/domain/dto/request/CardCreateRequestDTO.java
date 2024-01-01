package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardCreateRequestDTO {

    @NotEmpty(message = "please write content")
    private String content;

    @NotEmpty(message = "please write answer")
    private String answer;

    @NotEmpty(message = "please write time now")
    private LocalDateTime nowtime;

}
