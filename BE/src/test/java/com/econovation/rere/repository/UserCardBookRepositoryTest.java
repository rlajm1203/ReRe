package com.econovation.rere.repository;

import com.econovation.rere.entity.CardBook;
import com.econovation.rere.entity.User;
import com.econovation.rere.entity.UserCardBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserCardBookRepositoryTest {

    @Autowired
    private UserCardBookRepository ucbrepository;

    @Autowired
    private UserRepository urepository;

    @Autowired
    private CardBookRepository cbrepository;

//    어떤 사용자가 어떤 책을 담았을 때 상황을 구현한 테스트
    @Test
    void testUserCardBookInsert(){
        Optional<User> ou = this.urepository.findByNickname("KimJongMin");
        Optional<CardBook> oc = this.cbrepository.findById(4);

        if(ou.isPresent() && oc.isPresent()) {

            User user = ou.get();
            CardBook cardbook = oc.get();
            LocalDateTime time = LocalDateTime.now();
            UserCardBook ucb = new UserCardBook(user, cardbook, time);
            this.ucbrepository.save(ucb);
        }
    }

//    특정 사용자가 가진 카드북을 가져오는 테스트
    @Test
    void testUserCardBookSelect(){
        Optional<User> ou = this.urepository.findByNickname("KimJongMin");
        User user;
        List<UserCardBook> ucbs;
        if(ou.isPresent()){
            user = ou.get();
            ucbs = this.ucbrepository.findAllByUser(user);
            ucbs.forEach((ucb)-> System.out.println(ucb.getCardBook().getName()));
        }
    }
}