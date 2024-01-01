package com.econovation.rere.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

}
