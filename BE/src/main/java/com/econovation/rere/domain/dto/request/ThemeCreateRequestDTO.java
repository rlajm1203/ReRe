package com.econovation.rere.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeCreateRequestDTO {

    private Integer themeId;

    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
