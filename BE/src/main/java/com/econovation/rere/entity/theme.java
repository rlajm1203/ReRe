package com.econovation.rere.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer themeId;

    @Column(length = 60)
    private String themeName;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime createDate;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime updateDate;

//  테이블의 참조 관계를 명시적으로 표시해주는 역할
//  관계형 데이터의 외래키 라고 생각하면 편하다.
//  또는 ManyToOne은 부모 자식 관계를 갖는 구조에서 사용한다.
//  여기서 부모는 cardbook, 자식은 theme이다.
//  cardbook : theme 는 1:N 관계
   @ManyToOne
   private cardbook cardbook;

}
