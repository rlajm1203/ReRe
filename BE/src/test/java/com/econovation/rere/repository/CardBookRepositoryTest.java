package com.econovation.rere.repository;

import com.econovation.rere.entity.CardBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Test
    void testCardBookSelect(){
        List<CardBook> all = this.repository.findAll();

//        Assertions.assertEquals(기댓값, 실제값)
//        아래 메소드는 수행한 테스트의 결과가 맞는지 비교하기 위한 메소드이다.
//        기댓값과 실제값이 동일하지 않으면 테스트는 실패로 처리된다.
        Assertions.assertEquals(1,all.size());

        CardBook cardbook = all.get(0);
        Assertions.assertEquals("정보처리기사",cardbook.getName());
        Assertions.assertEquals(1, cardbook.getCardbookId());

    }

    @Test
    void testCardBookSelect2(){

//        Optional은 null 처리를 유연하게 처리하기 위해 사용하는 클래스이다.
//        isPresent() 메소드로 null인지 아닌지 확인한 후에 get으로 객체를 얻을 수 있다.
        Optional<CardBook> oq = this.repository.findById(1);

        if(oq.isPresent()){
            CardBook cardbook = oq.get();
            Assertions.assertEquals("정보처리기사", cardbook.getName());
            System.out.println(cardbook.getName());
        }

    }
}
