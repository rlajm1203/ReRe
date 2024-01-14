package com.econovation.rere.event;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
public class StudyAlarmEvent {

    private Integer themeId;
    private Integer step;
    private String message;

    @Override
    public String toString() {
        return message+" 목차 : "+themeId + "단계 : " + step;
    }
}
