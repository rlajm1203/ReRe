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
    @Column(name = "Id")
    private Integer completeId;

    // 어떤 회원이
    @ManyToOne(cascade = {CascadeType.REMOVE})
    private UserCardBook userCardBook;

    // 어떤 목차를 완료했는지를 나타내는
    @ManyToOne(cascade = {CascadeType.REMOVE})
    private Theme theme;

    // 학습 완료 시간
//    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime completeDate;

    // 학습 할 단계가 몇 단계인지
//    0 -> 학습을 하지 않은 상태
//    1 -> 첫 번째 학습을 완료한 상태
//    2 -> 두 번째 학습을 완료한 상태
//    3 -> 세 번째 학습을 완료한 상태
//    4 -> 네 번째 학습을 완료한 상태
    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer step;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer count;

    public StudyComplete(){}

}
