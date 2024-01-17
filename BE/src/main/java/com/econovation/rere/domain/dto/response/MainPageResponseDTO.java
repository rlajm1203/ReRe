package com.econovation.rere.domain.dto.response;

import com.econovation.rere.domain.entity.CardBook;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MainPageResponseDTO {

    private List<CardBookResponseDTO> originCardbooks;
    private List<CardBookResponseDTO> myCardbooks;

}
