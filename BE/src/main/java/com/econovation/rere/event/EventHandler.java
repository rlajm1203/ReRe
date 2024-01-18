package com.econovation.rere.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {

    @EventListener
    public void sendStudyAlarm(StudyAlarmEvent event){

    }
}
