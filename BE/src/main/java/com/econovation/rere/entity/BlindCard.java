package com.econovation.rere.entity;

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
}
