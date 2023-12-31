package com.econovation.rere.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
public class UserCardBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCardBookId;

    @ManyToOne
    private User user;

    @ManyToOne
    private CardBook cardBook;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime chooseDate;

}
