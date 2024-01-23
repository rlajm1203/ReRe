package com.econovation.rere.domain.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageResponseDTO {
    private byte[] imageData;
    private String contentType;

    public ImageResponseDTO(byte[] imageData, String contentType) {
        this.imageData = imageData;
        this.contentType = contentType;
    }
}