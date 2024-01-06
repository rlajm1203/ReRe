package com.econovation.rere.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(length = 600, nullable = false)
    private String content;

    @Column(length = 300, nullable = false)
    private String answer;

//    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime createDate;

//    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @ManyToOne
////  card : theme는 N:1 관계
    private Theme theme;

    public Card(){}
}
