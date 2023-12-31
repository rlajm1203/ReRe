package com.econovation.rere.repository;

import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CardRepositoryTest {

    @Autowired
    private CardRepository repository;

    @Test
    void testCardInsert(){
        Card card = new Card();
        LocalDateTime time = LocalDateTime.now();
//        card 엔티티의 content 속성은 질문에 해당
        card.setContent("2024년 1월 1일은 무슨 요일일까요?");
        card.setAnswer("월요일");

//        CardRepository의 findAll()을 사용해
//        해당하는 Theme을 찾아서 삽입
//        card.setTheme();
        card.setCreateDate(time);
        card.setUpdateDate(time);

        repository.save(card);
    }
}
