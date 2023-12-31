package com.econovation.rere.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCreateRequestDTO {

    private Long cardId;

    private String content;

    private String answer;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
