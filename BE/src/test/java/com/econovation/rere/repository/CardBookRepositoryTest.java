package com.econovation.rere.repository;

import com.econovation.rere.entity.CardBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CardBookRepositoryTest {
    @Autowired
    private CardBookRepository repository;

    @Test
    void testCardBookInsert(){
        CardBook cardBook = new CardBook();
        LocalDateTime time = LocalDateTime.now();
        cardBook.setName("정보처리기사");
        cardBook.setWriter("KimJongMin");
        cardBook.setScrapCnt(0);
        cardBook.setCreateDate(time);
        cardBook.setUpdateDate(time);

//      초기화한 cardBook DB에 insert
        this.repository.save(cardBook);
    }
}
