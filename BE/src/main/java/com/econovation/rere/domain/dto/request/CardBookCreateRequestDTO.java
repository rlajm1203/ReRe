package com.econovation.rere.domain.dto.request;

import com.econovation.rere.domain.entity.CardBook;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
public class CardBookCreateRequestDTO {

    @NotBlank(message = "please write cardbook name")
    private String name;

//    시간 정보는 프론트에서 전송 시점을 기준으로 하는 게 아니라
//    백엔드가 요청을 받은 시점을 기준으로 해야하는 듯

//    @NotEmpty(message = "please write time now")
//    private LocalDateTime timenow;

    // 이미지
    private MultipartFile image;

    public CardBook toEntity(String writer, LocalDateTime timenow, byte[] defaultImageData){
        byte[] imageData = null;
        try {
            if (image != null && !image.isEmpty()) {
                imageData = image.getBytes();
            } else {
                imageData = defaultImageData; // 기본 이미지 데이터 사용
            }
        } catch (IOException e) {
            throw new RuntimeException("이미지 파일 처리 중 오류가 발생했습니다.", e);
        }

        log.info("Image data size to be stored: " + (imageData != null ? imageData.length : "null"));

        return CardBook.builder()
                .name(name)
                .image(imageData)
                .writer(writer)
                .createDate(timenow)
                .updateDate(timenow)
                .scrapCnt(1)
                .build();
    }

}
