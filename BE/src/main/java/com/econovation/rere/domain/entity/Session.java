package com.econovation.rere.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;

    // 실제 세션 value는 Spring Session을 이용해야 할 듯
    @Column(length = 256)
    private String sessionValue;

    @Column(length = 20)
    private String loginId;

    @Column(length = 30)
    private String nickname;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime createDate;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime updateDate;

    public Session(){}

    public Session(String sessionValue, String loginId, String nickname, LocalDateTime time){
        this.sessionValue = sessionValue;
        this.loginId = loginId;
        this.nickname = nickname;
        this.createDate = time;
        this.updateDate = time;
    }

}
