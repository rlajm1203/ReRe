package com.econovation.rere.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@DynamicInsert
@SQLDelete(sql = "update card_book set deleted = true where cardbook_id = ?")
@Where(clause = "deleted = false")
// 엔티티를 데이터베이스에 등록하거나 엔티티를 수정할 때, 모든 필드를 업데이트 하는 방식으로 이루어진다.
// 즉, 특정 컬럼의 값만 수정할 때에도 UPDATE 쿼리에 모든 컬럼을 변경하도록 쿼리가 날아간다.
// 또한, 특정 필드 값이 등록되거나 수정되지 않아도 JPA가 쿼리를 날릴 때, null 값으로 보내게 된다.
// @ColumnDefault를 제대로 적용하려면 @DynamicInsert와 함께 사용해야 한다.
// @columnDefault 혼자 사용하면, 제대로 적용되지 않는 이유는 @DynamicInsert를 사용하지 않으면 JPA 기본 전략이 적용되어
// 아무것도 입력하지 않은 컬럼의 값에는 null 값이 들어간다. 이때 null 값도 값이므로
// 제대로 적용되지 않는 것이 당연하다.
// 따라서 @ColumnDefault 를 적용하기 위해서는 @DynamicInsert를 같이 사용해야 한다.
public class CardBook {

    // GeneratedValue를 사용하면 데이터를 추가할 때 해당 속성에 값을 따로 세팅하지 않아도 자동으로 1씩 증가
    // strategy는 고유 번호를 생성하는 옵션으로 GenerationType.IDENTITY를 많이 사용함
    // camel 케이스로 설정 시 DB에는 스네이크 케이스로 저장됨
    @Id // primary key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardbookId;

    // 엔티티 속성 지정
    // 테이블의 컬럼명을 지정, length는 컬럼의 길이를 지정할 때 사용
    @Column(length=30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String writer; // writer는 User.nickname과 동일

//    @Column(columnDefinition = "TEXT") // columnDefinition은 컬럼의 속성을 정의할 때 사용
    @Column(nullable = false)
    private LocalDateTime createDate;

//    @Column(columnDefinition = "TEXT") // 그 중 TEXT는 "내용"처럼 글자 수를 제한할 수 없을 경우에 사용한다.
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer scrapCnt;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted;


//  cardbook에서도 theme 목록에 접근할 수 있다.
//  cardbook : theme 은 1:N 관계
//  mappedBy에 들어갈 값은 Theme 클래스에 있는 CardBook 타입의 변수 명이다.
    @OneToMany(mappedBy = "cardbook", cascade = CascadeType.REMOVE)
    private List<Theme> themeList;

    @OneToMany(mappedBy = "cardbook", cascade = CascadeType.REMOVE)
    private List<UserCardBook> userCardBooks;

    @Lob
    private byte[] image;
    // 카드북의 이미지를 어떻게 하지?
    protected CardBook(){}

}
