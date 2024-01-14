package com.econovation.rere.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.io.Serializable;

import java.util.List;


@Entity
@Setter
@Getter
@SQLDelete(sql = "update user_card_book set deleted = true where user_id = ?")
@Where(clause = "deleted = false")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 20, unique = true, nullable = false)
    private String loginId;

    // 로그인 PW는 Bcrypt 방식으로 인코딩 할 예정
    // 타입을 String이 아니라 다른 타입으로 해야 할 듯?
    // 좀 더 알아보고 수정할 예정
    @Column(length = 256, nullable = false)
    private String pw;

    @Column(length = 30, unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserCardBook> userCardBooks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<BlindCard> blindCards;

    public User(){};

    @Builder
    public User(String loginId, String pw, String nickname){
        this.loginId = loginId;
        this.pw = pw;
        this.nickname = nickname;
    }

    public void updatePw(String pw) {
        this.pw = pw;
    }
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}
