package com.econovation.rere.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
public class StudyComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer completeId;

    // 어떤 회원이
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    // 어떤 목차를 완료했는지를 나타내는
    @ManyToOne(cascade = {CascadeType.ALL})
    private Theme theme;

    // 학습 완료 시간
//    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime completeDate;

    // 학습 할 단계가 몇 단계인지
    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer step;

    public StudyComplete(){}
}
