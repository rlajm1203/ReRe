package com.econovation.rere.domain.dto.response;

import com.econovation.rere.domain.entity.StudyComplete;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StudyCompleteResponseDTO {

    private String userName;
    private String themeName;
    private LocalDateTime completeDate;
    private Integer step;

//    studyComplete 객체와, 사용자 닉네임을 입력받음
    public static StudyCompleteResponseDTO toStudyCompleteResponseDTO(StudyComplete studyComplete, String nickname){
        return StudyCompleteResponseDTO.builder()
                .completeDate(studyComplete.getCompleteDate())
                .userName(nickname)
                .themeName(studyComplete.getTheme().getName())
                .step(studyComplete.getStep())
                .build();
    }

}
