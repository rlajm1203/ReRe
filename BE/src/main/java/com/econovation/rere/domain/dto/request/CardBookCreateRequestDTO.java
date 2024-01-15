package com.econovation.rere.domain.dto.request;

import com.econovation.rere.domain.entity.CardBook;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CardBookCreateRequestDTO {

    @NotBlank(message = "please write cardbook name")
    private String name;

//    시간 정보는 프론트에서 전송 시점을 기준으로 하는 게 아니라
//    백엔드가 요청을 받은 시점을 기준으로 해야하는 듯

//    @NotEmpty(message = "please write time now")
//    private LocalDateTime timenow;

    // 이미지
    private MultipartFile image;

    public CardBook toEntity(String writer, LocalDateTime timenow){
        byte[] imageData = null;
        try {
            // MultipartFile이 null이 아닐 때만 바이트 데이터로 변환
            if (image != null && !image.isEmpty()) {
                imageData = image.getBytes();
            }
        } catch (IOException e) {
            // 여기에서 IOException을 처리합니다.
            // 로그 기록, 사용자에게 오류 메시지 반환 등의 처리를 할 수 있습니다.
            // 예를 들어, RuntimeException을 던지거나, 커스텀 예외를 사용할 수 있습니다.
            throw new RuntimeException("이미지 파일 처리 중 오류가 발생했습니다.", e);
        }

        return CardBook.builder()
                .name(name)
                .image(imageData)
                .writer(writer)
                .createDate(timenow)
                .updateDate(timenow)
                .scrapCnt(1) // 카드북을 생성하면 무조건 나의 카드북에 담기므로 스크랩 카운트를 1로 설정
                .build();
    }

}
