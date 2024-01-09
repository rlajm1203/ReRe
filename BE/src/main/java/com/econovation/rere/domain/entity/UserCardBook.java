package com.econovation.rere.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
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
@DynamicInsert
@SQLDelete(sql = "update user_card_book set deleted = true where user_cardbook_id = ?")
@Where(clause = "deleted = false")
public class UserCardBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCardbookId;

    //    @Column(columnDefinition = "TEXT")
    @Column(nullable = false)
    private LocalDateTime chooseDate;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted; // 삭제 여부 기본 값 false

    @ManyToOne
    private User user;

    @ManyToOne
    private CardBook cardbook;

    @OneToMany(mappedBy = "userCardBook", cascade = CascadeType.REMOVE)
    private List<StudyComplete> studyCompletes;

    public UserCardBook(){}

}
