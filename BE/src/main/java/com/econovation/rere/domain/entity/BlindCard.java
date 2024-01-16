package com.econovation.rere.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLDelete(sql = "update blind_card set deleted = true where blind_id = ?")
@Where(clause = "deleted = false")
public class BlindCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Card card;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted;

    public BlindCard(){}

}
