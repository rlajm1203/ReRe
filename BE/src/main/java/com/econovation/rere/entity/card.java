package com.econovation.rere.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class card {

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
    private theme theme;


}
