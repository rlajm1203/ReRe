package com.econovation.rere.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 20, unique = true)
    private String loginId;

    // 로그인 PW는 SHA-256 방식으로 인코딩 할 예정
    // 타입을 String이 아니라 다른 타입으로 해야 할 듯?
    // 좀 더 알아보고 수정할 예정
    @Column(length = 256)
    private String pw;

    @Column(length = 30, unique = true)
    private String nickname;

    public User(){};

    public User(String loginId, String pw, String nickname){
        this.loginId = loginId;
        this.pw = pw;
        this.nickname = nickname;
    }
}
