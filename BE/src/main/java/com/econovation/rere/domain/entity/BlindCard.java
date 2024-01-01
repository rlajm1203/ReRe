package com.econovation.rere.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class BlindCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Card card;

    public BlindCard(){}

    public BlindCard(User user, Card card){
        this.user = user;
        this.card = card;
    }

}
