package com.econovation.rere.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(length = 600)
    private String content;

    @Column(length = 300)
    private String answer;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime createDate;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime updateDate;

    @ManyToOne
//  card : theme는 N:1 관계
    private Theme theme;

    public Card(){}

    public Card(String content, String answer, LocalDateTime time){
        this.content = content;
        this.answer = answer;
        this.createDate = time;
        this.updateDate = time;
    }

}
