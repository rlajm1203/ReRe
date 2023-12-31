package com.econovation.rere.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardBookDTO {

    private Integer cardbookId;

    private String name;

    private String writer;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer scrapCnt;

}
