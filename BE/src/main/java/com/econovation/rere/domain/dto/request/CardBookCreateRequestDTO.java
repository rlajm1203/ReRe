package com.econovation.rere.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookCreateRequestDTO {

    @NotEmpty(message = "please write cardbook name")
    private String name;

    @NotEmpty(message = "please write cardbook writer")
    private String writer;

    @NotEmpty(message = "please write time now")
    private LocalDateTime timenow;

}
