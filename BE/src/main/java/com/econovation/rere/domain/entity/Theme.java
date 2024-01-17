package com.econovation.rere.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLDelete(sql = "update theme set deleted = true where theme_id = ?")
@Where(clause = "deleted = false")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer themeId;

    @Column(length = 60, nullable = false)
    private String name;

//    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime createDate;

//    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted;

//  테이블의 참조 관계를 명시적으로 표시해주는 역할
//  관계형 데이터의 외래키 라고 생각하면 편하다.
//  또는 ManyToOne은 부모 자식 관계를 갖는 구조에서 사용한다.
//  여기서 부모는 cardbook, 자식은 theme이다.
//  theme : cardbook은 N:1 관계
    @ManyToOne
    private CardBook cardbook;

//  theme : card는 1:N 관계
//  mappedBy에 들어갈 값은 Card 클래스에 있는 Theme 타입 변수 명이다.
    @OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE)
    private List<Card> cardList;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE)
    private List<StudyComplete> studyCompletes;

    public Theme(){}

//    createDate 와 updateDate의 경우에는 프론트에서 시간을 넘겨주면 그 값을 저장
//    이 후 updateDate를 변경할 때는 setter를 이용


}
