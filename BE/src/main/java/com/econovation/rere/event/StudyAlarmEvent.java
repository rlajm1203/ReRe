package com.econovation.rere.event;


import lombok.*;

@Builder
@Data
@Setter
@Getter
public class StudyAlarmEvent {

    private Integer userId;
    private Integer themeId;
    private Integer step;
    private String message;

}
