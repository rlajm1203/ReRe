package com.econovation.rere.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDTO {

    private Integer themeId;

    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
