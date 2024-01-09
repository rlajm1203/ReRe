package com.econovation.rere.repository;

import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CardBookRepositoryTest {

    @Autowired
    private CardBookRepository repository;

//  카드북 생성 테스트
    @Test
    void testCardBookInsert(){
        for(int i=0; i<100; i++) {
            LocalDateTime time = LocalDateTime.now();
            CardBook cardBook = CardBook.builder()
                    .name("AdminTest"+i)
                    .writer("admin")
                    .scrapCnt(i)
                    .createDate(time)
                    .updateDate(time)
                    .build();

//      초기화한 cardBook DB에 insert
            this.repository.save(cardBook);
        }
    }

//    조회 테스트
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
//    조회 테스트
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

    @Test
    void testCardBookSearch(){
        List<CardBook> cardbooks = this.repository.findByName("정보처리기사");

        cardbooks.forEach((cardBook)-> System.out.println(cardBook.getName()));
    }



//    수정 테스트
    @Test
    void testCardBookModify(){
        Optional<CardBook> oc = this.repository.findById(1);

        if(oc.isPresent()){
            CardBook cardBook = oc.get();
            cardBook.setName("정보보안기사");
            this.repository.save(cardBook);
        }
    }

//    삭제 테스트
    @Test
    void testCardBookDelete(){
        repository.deleteById(1);
    }

    @Test
    @Transactional
        // @Transactional은 DB에 두 번 이상 접근할 때 사용하는 애너테이션으로 이 애너테이션이 붙은 메소드가 실행되는 동안 DB 세션이 유지된다.
    void testCardBookSelectAndTheme(){
        Optional<CardBook> oc = this.repository.findByNameAndWriter("정보보안기사","KimJongMin");

        if(oc.isPresent()) {
            CardBook cardBook = oc.get();
            Assertions.assertEquals(1, cardBook.getCardbookId());
        }
    }

    @Test
    @Transactional
    void testCardBookSelectThemeList(){
//        해당 CardBook에 속한 theme 들을 찾기
    }
}
