package com.econovation.rere.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class StudyComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer completeId;

    // 어떤 회원이
    @ManyToOne
    private User user;

    // 어떤 목차를 완료했는지를 나타내는
    @ManyToOne
    private Theme theme;

    // 학습 완료 시간
    @Column(columnDefinition = "TEXT")
    private LocalDateTime completeDate;

    // 학습 할 단계가 몇 단계인지
    @Column
    private Integer step;

    public StudyComplete(){}

    public StudyComplete(User user, Theme theme, LocalDateTime time){
        this.user = user;
        this.theme = theme;
        this.completeDate = time;
    }

}
