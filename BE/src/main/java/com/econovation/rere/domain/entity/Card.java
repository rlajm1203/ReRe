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
@SQLDelete(sql = "update card set deleted = true where card_id = ?")
@Where(clause = "deleted = false")
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

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted;

    @ManyToOne
////  card : theme는 N:1 관계
    private Theme theme;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BlindCard> blindCards;

    public Card(){}
}
