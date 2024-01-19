package com.econovation.rere.domain.dto.request;

import com.econovation.rere.domain.dto.response.ThemeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardbookThemesResponseDTO {
    private String cardbookName;
    private List<ThemeResponseDTO> themes;
}
