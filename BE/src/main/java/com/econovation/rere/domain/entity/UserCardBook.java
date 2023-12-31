package com.econovation.rere.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class UserCardBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCardbookId;

    @ManyToOne
    private User user;

    @ManyToOne
    private CardBook cardbook;

    @Column(columnDefinition = "TEXT")
    private LocalDateTime chooseDate;

    public UserCardBook(){}

    public UserCardBook(User user, CardBook cardbook, LocalDateTime time){
        this.user = user;
        this.cardbook = cardbook;
        this.chooseDate = time;
    }

}
