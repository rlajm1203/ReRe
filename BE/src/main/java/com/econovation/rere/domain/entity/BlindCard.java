package com.econovation.rere.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlindCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindId;

    @ManyToOne
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @Column(nullable = false)
    private Card card;

    public BlindCard(){}

}
