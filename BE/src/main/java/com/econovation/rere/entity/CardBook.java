package com.econovation.rere.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class CardBook {

    @Id // primary key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GeneratedValue를 사용하면 데이터를 추가할 때 해당 속성에 값을 따로 세팅하지 않아도 자동으로 1씩 증가
    // strategy는 고유 번호를 생성하는 옵션으로 GenerationType.IDENTITY를 많이 사용함
    private Integer cardbookId; // camel 케이스로 설정 시 DB에는 스네이크 케이스로 저장됨

    // 엔티티 속성 지정

    @Column(length=30) // 테이블의 컬럼명을 지정, length는 컬럼의 길이를 지정할 때 사용
    private String name;

    @Column(length = 30)
    private String writer;

    @Column(columnDefinition = "TEXT") // columnDefinition은 컬럼의 속성을 정의할 때 사용
    private LocalDateTime createDate;

    @Column(columnDefinition = "TEXT") // 그 중 TEXT는 "내용"처럼 글자 수를 제한할 수 없을 경우에 사용한다.
    private LocalDateTime updateDate;

    @Column
    private Integer scrapCnt;

//  cardbook에서도 theme 목록에 접근할 수 있다.
//  cardbook : theme 은 1:N 관계
    @OneToMany(mappedBy = "Theme", cascade = CascadeType.REMOVE)
    private List<Theme> themeList;

    // 카드북의 이미지를 어떻게 하지?
}
